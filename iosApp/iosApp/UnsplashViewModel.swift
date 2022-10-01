import SharedKit

let TAG = "UnsplashViewModel"

class UnsplashViewModel: ObservableObject {

    @Published var items: [Image] = []

    func fetchImages() {
        UnsplashClient.shared.fetchImages { items in
            Logger().d(tag: TAG, message: "fetchImages: \(items.count) items")
            DispatchQueue.main.async {
                self.items = items
            }
        }
    }
}
