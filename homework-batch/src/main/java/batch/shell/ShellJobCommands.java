package batch.shell;

import lombok.SneakyThrows;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static batch.config.JobConfig.COPY_JOB;

@ShellComponent
public class ShellJobCommands {

    private final JobOperator jobOperator;

    public ShellJobCommands(JobOperator jobOperator) {
        this.jobOperator = jobOperator;
    }

    @SneakyThrows
    @ShellMethod(value = "start job", key = {"startJob", "sJob", "SJ"})
    public void startCopyJob() {
        Long execId = jobOperator.start(COPY_JOB, "");
        System.out.println("\n\n" +jobOperator.getSummary(execId));
    }
}
