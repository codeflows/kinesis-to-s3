Application for copying Amazon Kinesis data to S3.

### Usage

1. [Download the app](TODO)
2. Configure your application (start with the template `KinesisToS3.SAMPLE.properties`)
3. Configure AWS access credentials:
    1. Locally
        1. Set the environment variables `AWS_ACCESS_KEY_ID=xxx` and `AWS_SECRET_KEY=yyy`
        2. or the JVM options `-Daws.accessKeyId=xxx` and `-D=aws.secretKey=yyy`
    2. On Amazon EC2
        1. No configuration needed if you start the server instance with a proper IAM role.
4. Run the jar: `java -jar kinesis-to-s3.jar` [configuration.properties]

### Building the jar from source

`mvn package`

creates a runnable jar under ./target/

### Development

See the main class `com.github.codeflows.aws.KinesisToS3`

### Rationale

The Kinesis sample code is complex, this project does one thing in a couple of lines of code.

### Notes

The project contains `amazon-kinesis-connectors` as a Git submodule [because it's not offered as a Maven dependency yet.](https://github.com/awslabs/amazon-kinesis-connectors/pull/11)
