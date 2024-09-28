package com.reachabl.events.controllers;

import com.reachabl.events.service.meeting.MeetingService;
import com.reachabl.models.meetings.Meeting;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/meeting")
public class MeetingController {
    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping
    public ResponseEntity<Meeting> createMeeting(@RequestBody Meeting meeting) {
        return ResponseEntity.ok(meetingService.saveMeeting(meeting));
    }

    @GetMapping
    public ResponseEntity<List<Meeting>> getAllMeetingsByUserId() {
        return ResponseEntity.ok(meetingService.getUserMeetings());
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteMeeting(@RequestParam String meetingId) {
        return ResponseEntity.ok(meetingService.deleteMeeting(meetingId));
    }
}
