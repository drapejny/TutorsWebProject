package by.slizh.tutorsweb.controller.command;

/**
 * Class Router which instance encapsulates page path and transition type to this page.
 *
 * @author Anton Pysk
 */

public class Router {

    public enum RouteType {
        FORWARD, REDIRECT
    }

    private String pagePath;
    private RouteType routeType = RouteType.FORWARD;

    public Router(String pagePath, RouteType routeType) {
        this.pagePath = pagePath;
        this.routeType = routeType;
    }

    public String getPagePath() {
        return pagePath;
    }

    public RouteType getRouteType() {
        return routeType;
    }

}
