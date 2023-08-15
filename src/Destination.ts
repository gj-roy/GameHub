export class Destination {
    public route: string;


    constructor(route: string) {
        this.route = route;

    }

    // The destinations you can reach through the bottom tab bar:
    public static Discover = new Destination("Discover");
    public static Likes = new Destination("Likes");
    public static News = new Destination("News");
    public static Settings = new Destination("Settings");


}
