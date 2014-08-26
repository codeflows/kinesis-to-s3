package com.github.codeflows.aws;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.kinesis.connectors.KinesisConnectorConfiguration;
import com.amazonaws.services.kinesis.connectors.KinesisConnectorExecutorBase;
import com.amazonaws.services.kinesis.connectors.KinesisConnectorRecordProcessorFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class KinesisToS3 extends KinesisConnectorExecutorBase<byte[], byte[]> {
    private final KinesisConnectorConfiguration configuration;

    public KinesisToS3(String configurationFile) throws IOException {
        final Properties properties = readProperties(configurationFile);
        configuration = new KinesisConnectorConfiguration(properties, new DefaultAWSCredentialsProviderChain());
        initialize(configuration);
    }

    private Properties readProperties(String file) throws IOException {
        final Properties properties = new Properties();
        final Reader reader = new FileReader(file);
        try {
            properties.load(reader);
        } finally {
            reader.close();
        }
        return properties;
    }

    @Override
    public KinesisConnectorRecordProcessorFactory<byte[], byte[]> getKinesisConnectorRecordProcessorFactory() {
        return new KinesisConnectorRecordProcessorFactory<>(new S3Pipeline(), configuration);
    }

    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.err.println("Please provide yourConfiguration.properties as the first argument to this program");
            System.exit(1);
        }

        KinesisToS3 app = new KinesisToS3(args[0]);
        app.run();
    }
}