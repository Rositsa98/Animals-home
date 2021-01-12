package fmi.course.hcmi.animalshome.model;

import fmi.course.hcmi.animalshome.dto.WorkDayDto;
import fmi.course.hcmi.animalshome.entity.WorkDay;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

public class ShelterInfo extends UserInfo {
    private WorkDayDto workDayDto;

    public ShelterInfo(){
        super();
    }

    public ShelterInfo(final String username,
                       final String address,
                       final String email,
                       final String phoneNumber,
                       final String roles,
                       final WorkDayDto workDayDto) {
        super(username, address, email, phoneNumber, roles);
        this.workDayDto = workDayDto;
    }

    public WorkDayDto getWorkDayDto() {
        return workDayDto;
    }

    public void setWorkDayDto(final WorkDayDto workDayDto) {
        this.workDayDto = workDayDto;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final ShelterInfo that = (ShelterInfo) o;
        return workDayDto.equals(that.workDayDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), workDayDto);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("ShelterInfo{");
        sb.append("workDayDto=")
                .append(workDayDto);
        sb.append('}');
        return sb.toString();
    }
}
