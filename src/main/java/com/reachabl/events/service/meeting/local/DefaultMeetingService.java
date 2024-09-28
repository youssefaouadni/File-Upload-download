package com.reachabl.events.service.meeting.local;

import com.reachabl.events.events.setup_reminder.SetupReminderEvent;
import com.reachabl.events.service.meeting.MeetingService;
import com.reachabl.events.utils.UserUtils;
import com.reachabl.models.meetings.Meeting;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DefaultMeetingService implements MeetingService {
    private final MeetingRepository meetingRepository;
    private final ApplicationEventPublisher publisher;
    private final ProfileRepository profileRepository   ;
    public DefaultMeetingService(MeetingRepository meetingRepository, ApplicationEventPublisher publisher, ProfileRepository profileRepository) {
        this.meetingRepository = meetingRepository;
        this.publisher = publisher;
        this.profileRepository = profileRepository;
    }

    @Override
    public Meeting saveMeeting(Meeting meeting) {
        try {
            if (meeting.getReminder() != null) {
                CompletableFuture.runAsync(() -> publisher.publishEvent(
                        new SetupReminderEvent(this,
                                meeting.getTime().atTime(meeting.getMeetingTime().startTime()),
                                meeting.getParticipants(),
                                meeting.getReminder(),
                                meeting.getZoneId(),
                                meeting.getTopic())));
            }
            return meetingRepository.save(meeting);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Meeting> getUserMeetings() {
        try {
            return meetingRepository.findByParticipantOrCreatorId(profileRepository.findByUserId(UserUtils.getUserIdFromToken()).get().getId());
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean deleteMeeting(String meetingId) {
        try {
            meetingRepository.deleteById(meetingId);
            return true;
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e);
        }
    }
}
