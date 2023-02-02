package com.slcube.shop.common.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class EmbeddedRedisConfig {

    @Value("${spring.redis.port}")
    private int port;

    private RedisServer redisServer;

    @PostConstruct
    public void startRedis() throws IOException {
        redisServer = new RedisServer(isRedisRunning() ? findAvailablePort() : port);
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer.isActive()) {
            redisServer.stop();
        }
    }

    private boolean isRedisRunning() throws IOException {
        return isRunning(excuteGrepProcess(port));
    }

    public int findAvailablePort() throws IOException {
        for (int port = 10000; port <= 65535; port++) {
            Process process = excuteGrepProcess(port);

            if (!isRunning(process)) {
                return port;
            }
        }

        throw new IllegalArgumentException("Not Found Available Port : 10000 ~ 65535");
    }

    private Process excuteGrepProcess(int port) throws IOException {
        String osName = System.getProperty("os.name").toLowerCase();
        String command;
        if (osName.contains("mac") || osName.contains("linux")) {
            command = String.format("netstat -nat | grep LISTEN|grep %d", port);
            String[] shell = {"/bin/sh", "-c", command};
            return Runtime.getRuntime().exec(shell);
        } else if (osName.contains("window")) {
            command = String.format("netstat -ano | find %d", port);
            String[] cmd = {"cmd", "/c", command};
            return Runtime.getRuntime().exec(cmd);
        }

        throw new IllegalStateException("Not Supported OS");
    }

    private boolean isRunning(Process process) {
        String line;
        StringBuilder pidInfo = new StringBuilder();

        try (BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            while ((line = input.readLine()) != null) {
                pidInfo.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return StringUtils.hasText(pidInfo.toString());
    }
}
