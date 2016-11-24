package com.netease.cloud;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.InspectImageResponse;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;

/**
 * Created by gingko on 23/11/2016.
 */
public class DockerJava {

    public static void main(String[] args) {
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("tcp://your-docker-host:2375")
                .withApiVersion("1.23")
                .withRegistryUrl("http://your-docker-host:2375")
                .withRegistryUsername("username")
                .withRegistryPassword("password")
                .withRegistryEmail("email")
                .build();
        DockerClient docker = DockerClientBuilder.getInstance(config).build();
        inspectImage(docker, "your imageId");

    }

    private static void inspectImage(DockerClient dockerClient, String imageId) {
        InspectImageResponse imageResponse = dockerClient.inspectImageCmd(imageId).exec();
        System.out.printf("Env: %s \n", imageResponse.getConfig().getEnv());
        System.out.printf("Entrypoint: %s \n", imageResponse.getConfig().getEntrypoint());
        System.out.printf("Volume: %s \n", imageResponse.getConfig().getVolumes());
        System.out.printf("ExposedPorts: %s \n", imageResponse.getConfig().getExposedPorts());

    }
}
