import SharedKit

public class UnsplashClient {
    private init() { }

    public typealias UnsplashHandler = (_ items: [Image]) -> Void

    public static let shared = UnsplashClient()

    private let presenter = ServiceLocator.init().getUnsplashPresenter

    private var handler: UnsplashHandler?

    public func fetchImages(completion: @escaping UnsplashHandler) {
        presenter.fetchImages(cb: self)
        handler = completion
    }
}

extension UnsplashClient: UnsplashData {
    public func onNewDataAvailable(items: [Image], e: KotlinException?) {
        Logger().d(tag: TAG, message: "onNewDataAvailable: \(items)")
        self.handler?(items)
    }
}
