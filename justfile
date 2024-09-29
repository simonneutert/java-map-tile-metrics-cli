default:
  @just --list

build:
  ./gradlew build

clean:
  ./gradlew clean

run:
  ./gradlew run

jar:
  ./gradlew jar

release:
  ./gradlew clean && ./gradlew build && ./gradlew jar