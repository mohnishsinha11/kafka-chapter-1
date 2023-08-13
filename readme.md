# kafka cli basic commands(for windows machine, for mac just change the folder name)

## start zookeper
`.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties`  

## start kafka server
`.\bin\windows\kafka-server-start.bat .\config\server.properties`

## create topic

* with default configurations  
`.\bin\windows\kafka-topics.bat --create --topic my-first-topic --bootstrap-server localhost:9092`
* custom configurations  
`.\bin\windows\kafka-topics.bat --create --topic my-second-topic --partitions 5 --replication-factor 1 --bootstrap-server localhost:9092`

## list topics
`.\bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092`

## describe topic
`.\bin\windows\kafka-topics.bat --describe my-first-topic --bootstrap-server localhost:9092`

## delete topic
`auto.create.topics.enable = false`  
`.\bin\windows\kafka-topics.bat --delete --topic my-first-topic --bootstrap-server localhost:9092`

## produce on topic
`.\bin\windows\kafka-console-producer.bat --topic my-first-topic --bootstrap-server localhost:9092`

## consume on topic
`.\bin\windows\kafka-console-consumer.bat --topic my-first-topic --bootstrap-server localhost:9092`