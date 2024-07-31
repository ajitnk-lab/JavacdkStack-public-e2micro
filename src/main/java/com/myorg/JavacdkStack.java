package com.myorg;

import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;

import java.util.List;

import software.amazon.awscdk.Duration;
import software.amazon.awscdk.services.sqs.Queue;

import software.amazon.awscdk.services.ec2.*;

public class JavacdkStack extends Stack {
    public JavacdkStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public JavacdkStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        // The code that defines your stack goes here

        // // example resource
        // final Queue queue = Queue.Builder.create(this, "JavacdkQueue")
        //          .visibilityTimeout(Duration.seconds(300))
        //          .build();

          // Create a VPC with one public subnet
        Vpc vpc = Vpc.Builder.create(this, "MyVpc")
                .maxAzs(1)
                .subnetConfiguration(List.of(
                    SubnetConfiguration.builder()
                            .cidrMask(24)
                            .name("Public")
                            .subnetType(SubnetType.PUBLIC)
                            .build()
                ))
                .build();

        // Create an EC2 instance in the public subnet
        Instance instance = Instance.Builder.create(this, "MyInstance")
                .vpc(vpc)
                .instanceType(InstanceType.of(InstanceClass.T2, InstanceSize.MICRO))
                .machineImage(MachineImage.latestAmazonLinux2023())
                .build();
    }
}
