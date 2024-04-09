import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {

    public final static  double LONDPP = Math.abs(MapServer.ROOT_LRLON-MapServer.ROOT_ULLON)/MapServer.TILE_SIZE;

    private String[][] render_grid;
    private double raster_ul_lon;
    private double raster_ul_lat;
    private double raster_lr_lon;
    private double raster_lr_lat;
    private int depth;
    private boolean query_success = false;

    public Rasterer() {
        // YOUR CODE HERE
    }
    private boolean validParam(Map<String, Double> params) {
        return params.containsKey("lrlon") && params.containsKey("ullon")
                && params.containsKey("w") && params.containsKey("h")
                && params.containsKey("lrlat") && params.containsKey("ullat");
    }

    private int getDepth(double needLonDPP) {
        int depth = 0;
        double initLonDPP = LONDPP;
        while (depth < 7 && initLonDPP > needLonDPP) {
            initLonDPP /= 2;
            depth++;
        }
        return depth;
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        Map<String, Object> results = new HashMap<>();
        Rasterer rasterer = new Rasterer();
        if(!validParam(params)){
            rasterer.query_success = false;
            results.put("query_success",rasterer.query_success);
            return results;
        }
        //System.out.println(params);
        double lrlon = params.get("lrlon");
        double ullon = params.get("ullon");
        double w = params.get("w");
        double h = params.get("h");
        double ullat = params.get("ullat");
        double lrlat = params.get("lrlat");

        //get the depth of rasterer
        rasterer.depth = getDepth((lrlon-ullon)/w);

        //get raster_ul_lon, raster_ul_lat, raster_lr_lon and raster_lr_lat of rasterer
        double xPartition =(MapServer.ROOT_LRLON - MapServer.ROOT_ULLON) / Math.pow(2,rasterer.depth);
        double yPartition =(MapServer.ROOT_LRLAT - MapServer.ROOT_ULLAT) / Math.pow(2,rasterer.depth);
        int xBegin = (int)((ullon - MapServer.ROOT_ULLON)/xPartition);
        int yBegin = (int)((ullat - MapServer.ROOT_ULLAT)/yPartition);
        int xEnd = (int)Math.pow(2,rasterer.depth) -1 - (int)((MapServer.ROOT_LRLON - lrlon)/xPartition);
        int yEnd = (int)Math.pow(2,rasterer.depth) -1 - (int)((MapServer.ROOT_LRLAT - lrlat)/yPartition);
        rasterer.raster_ul_lon = xBegin*xPartition;
        rasterer.raster_ul_lat = yBegin*yPartition;
        rasterer.raster_lr_lon = xEnd*xPartition;
        rasterer.raster_lr_lat = yEnd*yPartition;

        //get render_grid of rasterer
        rasterer.render_grid = new String[yEnd-yBegin+1][xEnd-xBegin+1];
        for(int i = yBegin, y = 0;i<=yEnd;i++,y++)
            for(int j = xBegin,x = 0;j<=xEnd;j++,x++)
                rasterer.render_grid[y][x] = "d"+rasterer.depth+"_"+"x"+j+"_"+"y"+i+".png";
        //set query_success to true on success
        rasterer.query_success = true;

        //put results by rasterer
        results.put("render_grid",rasterer.render_grid);
        results.put("raster_ul_lon",rasterer.raster_ul_lon);
        results.put("raster_ul_lat",rasterer.raster_ul_lat);
        results.put("raster_lr_lon",rasterer.raster_lr_lon);
        results.put("raster_lr_lat",rasterer.raster_lr_lat);
        results.put("depth",rasterer.depth);
        results.put("query_success",rasterer.query_success);

        return results;
    }

}
