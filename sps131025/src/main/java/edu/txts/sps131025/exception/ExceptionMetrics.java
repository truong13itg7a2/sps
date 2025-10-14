package edu.txts.sps131025.exception;

import edu.txts.sps131025.constants.ErrorCode;
import org.springframework.stereotype.Component;
import org.w3c.dom.css.Counter;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class ExceptionMetrics {

	private final MeterRegistry meterRegistry;
	private final Counter businessExceptionCounter;
	private final Counter validationExceptionCounter;
	private final Counter genericExceptionCounter;

	public ExceptionMetrics(MeterRegistry meterRegistry) {
		this.meterRegistry = meterRegistry;

		// Initialize counters
		this.businessExceptionCounter = Counter.builder("app.exceptions.business")
				.description("Number of business exceptions")
				.register(meterRegistry);

		this.validationExceptionCounter = Counter.builder("app.exceptions.validation")
				.description("Number of validation exceptions")
				.register(meterRegistry);

		this.genericExceptionCounter = Counter.builder("app.exceptions.generic")
				.description("Number of generic exceptions")
				.register(meterRegistry);
	}

	public void recordBusinessException(ErrorCode errorCode) {
		businessExceptionCounter.increment();

		// Tag theo error code để monitoring chi tiết
		Counter.builder("app.exceptions.business.detail")
				.tag("errorCode", errorCode.getCode())
				.register(meterRegistry)
				.increment();
	}

	public void recordValidationException() {
		validationExceptionCounter.increment();
	}

	public void recordGenericException() {
		genericExceptionCounter.increment();
	}

	// Method để record execution time
	public void recordExecutionTime(String methodName, long durationMs) {
		Timer.builder("app.method.execution.time")
				.tag("method", methodName)
				.register(meterRegistry)
				.record(Duration.ofMillis(durationMs));
	}
}
