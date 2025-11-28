# Producer Consumer – A Thread Synchronization Project
This project Implements a classic producer-consumer pattern demonstrating thread
synchronization and communication. The program will simulate concurrent data transfer
between a producer thread that reads from a source container and places items into a shared
queue, and a consumer thread that reads from the queue and stores items in a destination
container.

Setup Instructions:

1)git clone https://github.com/adarsh-2503A/IntuitCodingChallenge.git
cd producer_consumer

2)Run the application
Open in IntelliJ → run Main.java
OR
via terminal:
javac Main.java
java com.example.pc.Main

Sample Output:

Producing: 1
Producing: 2
Producing: 3
Producing: 4
Producing: 5
Consuming: 1
Consuming: 2
Consuming: 3
Consuming: 4
Consuming: 5
Destination: [1, 2, 3, 4, 5]
