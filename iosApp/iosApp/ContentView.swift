import SwiftUI
import shared // here we are importing the shared module that has common infrastructural logic

// this is where we replace all the swift UI code with compose multiplatform logic
struct ContentView: View {
    var body: some View{
        ComposeView().ignoresSafeArea(.keyboard)
    }

}

struct ComposeView: UIViewControllerRepresentable{
    func makeUIViewController(context: Context) -> some UIViewController {
        MainIOSKt.MainViewController()
    }

    func updateViewController(_ uiViewController: UIViewControllerType, _ context: Context ) {

    }
}
