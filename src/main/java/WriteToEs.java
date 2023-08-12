import bean.Event;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class WriteToEs {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);

        DataStreamSource<Event> eventDataStreamSource = env.fromElements(new Event("Mary", "./home", "2023-08-07 12:12:23"),
                new Event("Bob", "./home", "2023-08-08 12:12:23"),
                new Event("Alice", "./home", "2023-08-09 12:12:23"),
                new Event("Bob", "./home", "2023-08-10 12:12:23"),
                new Event("Alice", "./home", "2023-08-11 12:12:23"),
                new Event("Bob", "./home", "2023-08-12 12:12:23"),
                new Event("Bob", "./home", "2023-08-13 12:12:23"),
                new Event("Bob", "./home", "2023-08-14 12:12:23"));


    }
}
