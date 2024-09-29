# Map Tile Metrics CLI

This is a command line interface (CLI) for the Map Tile Metrics library.\
It is a tool for generating metrics for map tiles or points in a (positive) coordinate system.

Please read in details about the library in the [Map Tile Metrics library README](https://github.com/simonneutert/java-map-tile-metrics).

Java 21 or higher is required to run the CLI.

## Using the CLI

The CLI is a Java application that can be run from the command line. It requires Java 17 or higher.

### Build

To build the CLI, you need to have Gradle installed. Then, you can run the following command:

```shell
$ ./gradle jar # will build app/build/libs/app.jar
$ cp app/build/libs/app.jar ~/Desktop/map-tile-metrics-cli.jar # copy the jar to the desktop (or any other location)
```

```shell
# cd into the directory where the jar is located now
#
# base command
$ java -jar map-tile-metrics-cli.jar

# it needs at least a single argument, being a json string
$ java -jar map-tile-metrics-cli.jar "$(cat path/to/test_data.json)"

# use `-f` or `--file` to read the json from a file
$ java -jar map-tile-metrics-cli.jar -f path/to/test_data.json
```

### Run with docker

The CLI can also be run using Docker. The Dockerfile is included in the repository. Please, keep in mind, that you only need to pass the JSON string as an argument to the Docker container or use the `-f`/`--file` in conjunction with the file path. As `java -jar map-tile-metrics-cli.jar` is already set as the default entrypoint.

```shell
# build
$ docker build -t jmtm .

# run
$ docker run --rm -it -v "$(pwd)"/results:/app/results jmtm "$(cat path/to/test_data.json)"
```

### Expected Data Input

The CLI expects a JSON string/file following this schema:

```json
[
  {
    "x": 1,
    "y": 2
  }
]
```

In words: An array of objects, each with an `x` and `y` property.

### Output

The CLI will write the metrics in `results/results.json`.

