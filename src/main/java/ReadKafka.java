import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

public class ReadKafka {
    public static void main(String[] args) {
        //设置 Kafka 相关参数
        Properties properties = new Properties();
        //设置 Kafka 的地址和端口
        properties.put("bootstrap.servers","hadoop102:9092,hadoop103:9092,hadoop104:9092");
        //key的反序列化器
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //value的反序列化器
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //设置消费者组 ID
        properties.setProperty("group.id", "g1");
        //读取偏移量策略：如果没有记录偏移量，就从头读，如果记录过偏移量，就接着读
        properties.setProperty("auto.offset.reset", "earliest");
        //没有开启 checkpoint，让 flink 提交偏移量的消费者定期自动提交偏移量
        properties.setProperty("enable.auto.commit", "true");

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //创建 FlinkKafkaConsumer 并传入相关参数
        FlinkKafkaConsumer<String> kafkaConsumer = new FlinkKafkaConsumer<>(
                "test",//要读取数据的 Topic 名称
                new SimpleStringSchema(), //读取文件的反序列化 Schema
                properties //传入 Kafka 的参数
        );

        //使用 addSource 添加 kafkaConsumer
        SingleOutputStreamOperator<String> zzz =
                env.addSource(kafkaConsumer)
                .setParallelism(1)
                .uid("123")
                .name("zzz");

        zzz.print("我是zzz：");

        try {
            env.execute("第一个task");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
