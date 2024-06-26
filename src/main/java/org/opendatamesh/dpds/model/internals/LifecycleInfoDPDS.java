package org.opendatamesh.dpds.model.internals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LifecycleInfoDPDS {

    @Schema(description = "List of Lifecycle Task Info objects of the Lifecycle Info", required = true)
    private List<LifecycleTaskInfoDPDS> tasksInfo;

    public LifecycleInfoDPDS() {
        tasksInfo = new ArrayList<LifecycleTaskInfoDPDS>();
    }   

    @JsonIgnore
    public Set<String> getStageNames() {
        Set<String> stageNames = new HashSet<String>();
        for(LifecycleTaskInfoDPDS taskInfo: tasksInfo) {
            stageNames.add(taskInfo.getStageName());
        }
        return stageNames;
    }

    @JsonIgnore
    public List<LifecycleTaskInfoDPDS> getTasksInfo(String stageName) {

        List<LifecycleTaskInfoDPDS> stageTasksInfo = new ArrayList<LifecycleTaskInfoDPDS>();
        
        Objects.requireNonNull(stageName, "Parameter stageName cannot be null");
        
        for(LifecycleTaskInfoDPDS taskInfo: tasksInfo) {
            if(stageName.equals(taskInfo.getStageName())) {
                stageTasksInfo.add(taskInfo);
            }
        }
        
        return stageTasksInfo;
    }

}
