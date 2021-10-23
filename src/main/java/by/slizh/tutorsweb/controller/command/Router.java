package by.slizh.tutorsweb.controller.command;

/**
 * Class Router which instance encapsulates page path and route type to this page.
 */
public class Router {

    /**
     * The enum of route types.
     */
    public enum RouteType {
        FORWARD,
        REDIRECT
    }

    private String pagePath;
    private RouteType routeType;

    /**
     * Instantiates a new Router.
     *
     * @param pagePath  the page path
     * @param routeType the route type
     */
    public Router(String pagePath, RouteType routeType) {
        this.pagePath = pagePath;
        this.routeType = routeType;
    }

    /**
     * Gets page path.
     *
     * @return the page path
     */
    public String getPagePath() {
        return pagePath;
    }

    /**
     * Gets route type.
     *
     * @return the route type
     */
    public RouteType getRouteType() {
        return routeType;
    }

}
