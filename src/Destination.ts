export class Destination {
    public route: string;
    public title: string;

    constructor(route: string, title: string) {
        this.route = route;
        this.title = title;
    }

    public static Discover = new Destination("discover", "Discover");
    public static Likes = new Destination("likes", "Likes");
    public static News = new Destination("news", "News");
    public static Settings = new Destination("settings", "Settings");
}
