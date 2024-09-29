/*
 * This source file was generated by the Gradle 'init' task
 */
package de.simon_neutert.map_tile_metrics_cli;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.jr.ob.JSON;
import com.fasterxml.jackson.jr.ob.JSONObjectException;

import de.simon_neutert.map_tile_metrics.*;

public class App {
    static HashSet<Point> readString(String content) {
        List<Point> jsonPoints = new ArrayList<Point>();
        try {
            jsonPoints = JSON.std.listOfFrom(Point.class, content);
        } catch (JSONObjectException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashSet<Point> points = new HashSet<Point>();
        jsonPoints.stream().forEach(point -> points.add(point));
        return points;
    }

    static HashSet<Point> readFile(String filePath) {
        String content = "";
        List<Point> jsonPoints = new ArrayList<Point>();

        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
            jsonPoints = JSON.std.listOfFrom(Point.class, content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashSet<Point> points = new HashSet<Point>();
        jsonPoints.stream().forEach(point -> points.add(point));
        return points;
    }

    static void writeFile(HashMap<String, ArrayList<HashSet<Point>>> clusterResult,
            int maxClustersSize,
            ArrayList<HashMap<Point, Integer>> maxSquares,
            int maxSquaresSize) {
        try {
            JsonResult result = new JsonResult(clusterResult, maxClustersSize, maxSquares, maxSquaresSize);
            JSON.std.write(result, new File("results/results.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HashSet<Point> points = new HashSet<Point>();

        if (args.length == 1) {
            System.out.println("assuming json string as input");

            points = readString(args[0]);
        } else if (args.length == 2 && (args[0].equals("--file") || args[0].equals("-f"))) {
            System.out.println("assuming --file and filename as input");

            points = readFile(args[1]);
        } else {
            throw new IllegalArgumentException("You must provide one or two arguments");
        }
        Clusters clusterService = new Clusters(points);
        HashMap<String, ArrayList<HashSet<Point>>> clusterResult = clusterService.calculate();

        int maxClustersSize = clusterResult.get("maxClusters").get(0).size();
        System.out.println("maxClusterSize: " + maxClustersSize);

        MaxSquares maxSquaresService = new MaxSquares(clusterResult.get("clusters"), points);
        ArrayList<HashMap<Point, Integer>> maxSquaresResult = maxSquaresService.calculate();

        int maxSquareSize = 0;
        if (maxSquaresResult.size() == 0) {
            System.out.println("No squares found");
        } else {
            System.out.println("maxSquaresResult: " + maxSquaresResult.get(0).values().toArray()[0]);
            maxSquareSize = maxSquaresResult.get(0).values().toArray(new Integer[0])[0];
        }
        writeFile(clusterResult, maxClustersSize, maxSquaresResult, maxSquareSize);
    }
}
