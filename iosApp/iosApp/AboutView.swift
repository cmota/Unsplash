import SwiftUI

struct AboutView: View {
    var body: some View {
        NavigationView {
            ZStack(alignment: .topLeading) {
                Color("ContentColor")
                ZStack {
                    Color("ContentColor")
                    Text("This is a showcase application built using SwiftUI that uses Unsplash API.")
                        .padding(.leading, 10)
                        .foregroundColor(.white)
                        .font(Font.custom("BigNoodleTitling", size: 22))
                        .multilineTextAlignment(.center)
                }
            }
            .navigationBarTitle("Unsplash", displayMode: .inline)
        }
    }
}
