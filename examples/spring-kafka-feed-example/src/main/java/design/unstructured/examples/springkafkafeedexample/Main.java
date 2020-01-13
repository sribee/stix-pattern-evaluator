package design.unstructured.examples.springkafkafeedexample;

import java.util.function.Consumer;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	public Function<KStream<String, String>, KStream<?, WordCount>> process() {

		return input -> input
                .flatMapValues(value -> Arrays.asList(value.toLowerCase().split("\\W+")))
                .map((key, value) -> new KeyValue<>(value, value))
                .groupByKey(Serialized.with(Serdes.String(), Serdes.String()))
                .windowedBy(TimeWindows.of(5000))
                .count(Materialized.as("word-counts-state-store"))
                .toStream()
                .map((key, value) -> new KeyValue<>(key.key(), new WordCount(key.key(), value,
						new Date(key.window().start()), new Date(key.window().end()))));
						
		return input -> input.foreach((key, value) -> {

		});
	}

}
