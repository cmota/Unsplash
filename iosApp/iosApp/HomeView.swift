import SDWebImageSwiftUI
import SharedKit
import SwiftUI

struct HomeView: View {
    @EnvironmentObject private var unsplashViewModel: UnsplashViewModel

    var body: some View {
        NavigationView {
            ZStack {
                Color("ContentColor")
                ScrollView(.vertical) {
                    VStack {
                        ForEach(unsplashViewModel.items, id: \.id) { item in
                            if (item.urls.regular == nil) {
                                Rectangle().foregroundColor(.gray)
                                Image("UnsplashIcon")
                            } else {
                                AnimatedImage(url: URL(string: "\(item.urls.regular!)"))
                                    .resizable()
                                    .scaledToFill()
                                    .cornerRadius(8)
                                    .overlay(ImageOverlay(item: item), alignment: .bottomLeading)
                            }
                        }
                    }
                }
            }
            .navigationBarTitle("Unsplash", displayMode: .inline)
        }
        .onAppear {
            Logger().d(tag: TAG, message: "Retrieving most trending images")
            unsplashViewModel.fetchImages()
        }
    }
}

struct ImageOverlay: View {
    var item: SharedKit.Image

    var body: some View {
        ZStack {
            Text(item.user.username)
                .padding(.leading, 10)
                .foregroundColor(.white)
                .font(Font.custom("BigNoodleTitling", size: 18))
        }
        .padding(10)
    }
}
