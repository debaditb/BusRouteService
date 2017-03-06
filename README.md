# BusRouteService

> The InputProcessor reads the route information from the file specified and calls BusRouteRepository to save the data in BusRouteDB. 
> BusRouteDB is an Enum holding the set of routes for every station ID provided.
> After the reading is done, BusRouteService starts an embedded jetty server, which exposes RouteResource to query the direct route information.
