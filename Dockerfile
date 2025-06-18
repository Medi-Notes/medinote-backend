# 빌드 스테이지
FROM amazoncorretto:17 as build
WORKDIR /app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew build -x test

# 실행 스테이지 (일종의 새로 os 설정하는 것)
FROM amazoncorretto:17-alpine as runtime
WORKDIR /app
# 빌드 스테이지의 jar 파일을 현재 스테이지로 복사
COPY --from=build /app/build/libs/bango-0.0.1-SNAPSHOT.jar /app/bango/server.jar

# 컨테이너 실행 시
CMD ["java", "-Xmx256m", "-Duser.timezone=Asia/Seoul", "-jar", "/app/bango/server.jar"]