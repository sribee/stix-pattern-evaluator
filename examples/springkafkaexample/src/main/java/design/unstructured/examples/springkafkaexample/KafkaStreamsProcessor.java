package design.unstructured.examples.springkafkaexample;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

/**
 * KafkaStreamsProcessor
 */
public interface KafkaStreamsProcessor {

    @Input("input")
    KStream<?, ?> input();

    @Output("output")
    KStream<?, ?> output();
}