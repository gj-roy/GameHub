export class Destination {
    public route: string;
    public title?: string;

    constructor(route: string, title?: string) {
        this.route = route;
        this.title = title;
    }

    // All the tabs you can go to via the bottom tab bar
    public static DiscoverTab = new Destination("discover-tab", "Discover");
    public static LikesTab = new Destination("likes-tab", "Likes");
    public static NewsTab = new Destination("news-tab", "News");
    public static SettingsTab = new Destination("settings-tab", "Settings");

    // All the children of the DiscoverTab:
    public static DiscoverScreen = new Destination("discover")
    public static GameScreen = new Destination("game")

}
