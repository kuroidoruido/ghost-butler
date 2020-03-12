FROM oracle/graalvm-ce:20.0.0-java11 as graalvm
# For JDK 11
#FROM oracle/graalvm-ce:19.3.1-java11 as graalvm
RUN gu install native-image

COPY . /home/app/ghost-butler
WORKDIR /home/app/ghost-butler

RUN native-image --no-server -cp build/libs/ghost-butler-*-all.jar

FROM frolvlad/alpine-glibc
RUN apk update && apk add libstdc++
EXPOSE 8080
COPY --from=graalvm /home/app/ghost-butler/ghost-butler /app/ghost-butler
ENTRYPOINT ["/app/ghost-butler"]
