import SwiftUI
import SharedKit

struct ContentView: View {
    @State private var tabSelection = 1

    @StateObject var unsplashViewModel = UnsplashViewModel()

    init() {
        let itemAppearance = UITabBarItemAppearance()
        itemAppearance.normal.iconColor = UIColor(Color.white)

        let tabBarAppearance = UITabBarAppearance()
        tabBarAppearance.backgroundColor = UIColor(Color("ContentColor"))
        tabBarAppearance.stackedLayoutAppearance = itemAppearance
        tabBarAppearance.inlineLayoutAppearance = itemAppearance
        tabBarAppearance.compactInlineLayoutAppearance = itemAppearance

        UITabBar.appearance().standardAppearance = tabBarAppearance
        if #available(iOS 15.0, *) {
            UITabBar.appearance().scrollEdgeAppearance = tabBarAppearance
        }

        let navBarAppearance = UINavigationBarAppearance()
        navBarAppearance.backgroundColor = UIColor(Color("ContentColor"))
        navBarAppearance.shadowImage = UIImage()
        navBarAppearance.shadowColor = .clear
        navBarAppearance.backgroundImage = UIImage()
        navBarAppearance.largeTitleTextAttributes = [.foregroundColor: UIColor.white]

        if let uiFont = UIFont(name: "BigNoodleTitling", size: 25) {
            navBarAppearance.titleTextAttributes = [
                .font: uiFont,
                .foregroundColor: UIColor.white
            ]}

        UINavigationBar.appearance().barTintColor = UIColor(Color("ContentColor"))
        UINavigationBar.appearance().isTranslucent = false
        UINavigationBar.appearance().standardAppearance = navBarAppearance
        UINavigationBar.appearance().scrollEdgeAppearance = navBarAppearance
        UINavigationBar.appearance().compactAppearance = navBarAppearance
    }

    var body: some View {
        TabView(selection: $tabSelection) {
            HomeView()
                .tabItem {
                    Image("HomeIcon")
                    Text("Home")
                }
                .tag(1)
                .environmentObject(unsplashViewModel)

            AboutView()
                .tabItem {
                    Image("AboutIcon")
                    Text("About")
                }
                .tag(2)
        }
        .accentColor(Color("PrimaryColor"))
    }
}
