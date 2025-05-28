//
//  ArticlesScreen.swift
//  iosApp
//
//  Created by Petros Efthymiou on 27/11/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

extension ArticlesScreen {
    
    @MainActor
    class ArticlesViewModelWrapper: ObservableObject { // An observable object is a reactive object
        // that can be used inside the SwiftUI
        let articlesViewModel: ArticlesViewModel
        
        
        init() {
            articlesViewModel = ArticlesInjector().articlesViewModel
            articlesState = articlesViewModel.articlesState.value
        }
        
        @Published var articlesState: ArticlesState // this is the actual publisher that will be exposed
        // it must be of the same sealed class type ArticleState
        
        func startObserving() {
            Task {
            // for any emission happening inside the view model's  articlesState, we are making the
            // same emission to the  published var self.articlesState
            // this function will be called by our UI once it appears
                for await articlesS in articlesViewModel.articlesState {
                    self.articlesState = articlesS
                }
            }
        }
    }
}

struct ArticlesScreen: View {
    
    @ObservedObject private(set) var viewModel: ArticlesViewModelWrapper //this will consume the stateflow
    //of the ArticlesViewModel and will expose an iosPublisher that will now emit info to the UI
    
    var body: some View {
        VStack {
            AppBar()
            
            switch viewModel.articlesState {
            case is ArticlesState.Loading:
                Loader()

            case let success as ArticlesState.Success:
                ScrollView {
                    LazyVStack(spacing: 10) {
                        ForEach(success.articles, id: \.self) { article in
                            ArticleItemView(article: article)
                        }
                    }
                }

            case let error as ArticlesState.Error:
                ErrorMessage(message: error.message)

            case is ArticlesState.Empty:
                Text("No articles available.")

            default:
                EmptyView()
            }

            
        }.onAppear{
            self.viewModel.startObserving()
        }
    }
}

struct AppBar: View {
    var body: some View {
        Text("Articles")
            .font(.largeTitle)
            .fontWeight(.bold)
    }
}

struct ArticleItemView: View {
    var article: Article
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            AsyncImage(url: URL(string: article.imageUrl)) { phase in
                if phase.image != nil {
                    phase.image!
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                } else if phase.error != nil {
                    Text("Image Load Error")
                } else {
                    ProgressView()
                }
            }
            Text(article.title)
                .font(.title)
                .fontWeight(.bold)
            Text(article.desc)
            Text(article.date).frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(.gray)
        }
        .padding(16)
    }
}

struct Loader: View {
    var body: some View {
        ProgressView()
    }
}

struct ErrorMessage: View {
    var message: String
    
    var body: some View {
        Text(message)
            .font(.title)
    }
}

