# jms-example

## Prerequisites

- [ActiveMQ](http://activemq.apache.org/components/classic/download/)
- Java 8+

## Getting Started

1. Start the ActiveMQ server

    ```
    cd apache-activemq-<version>/bin
    ./activemq start
    ```

2. Examples:
    - Point-to-point:
        - Run `Producer` main method
        - Run `Consumer` main method
    - Publisher/Subscriber:
        - Run `Subscriber` main method
        - Run `Publisher` main method

> In both examples you should be able to see some logs when a message is sent and received
