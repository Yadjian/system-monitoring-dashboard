package com.monitoring.system_monitoring_dashboard.controller;

import com.monitoring.system_monitoring_dashboard.model.AllMetricsDTO;
import com.monitoring.system_monitoring_dashboard.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/**
 * WebSocket controller for real-time metrics broadcasting.
 */
@Controller
@EnableScheduling
public class MetricsWebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MonitoringService monitoringService;

    /**
     * Broadcast metrics to all connected clients every 1 second.
     */
    @Scheduled(fixedRate = 1000)
    public void broadcastMetrics() {
        AllMetricsDTO metrics = monitoringService.getAllMetrics();
        messagingTemplate.convertAndSend("/topic/metrics", metrics);
    }
}
