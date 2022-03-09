package ca.mcgill.ecse321.grocerystoresystem.dto;

import java.time.LocalDateTime;

public class SpecialDayDto {
  private int id;
  private LocalDateTime startTimestamp;
  private LocalDateTime endTimestamp;
  
  public SpecialDayDto(LocalDateTime startTimestamp, LocalDateTime endTimestamp, int id) {
    this.startTimestamp = startTimestamp;
    this.endTimestamp = endTimestamp;
    this.id = id;
  }

  public SpecialDayDto(LocalDateTime startTimestamp, LocalDateTime endTimestamp) {
      this.startTimestamp = startTimestamp;
      this.endTimestamp = endTimestamp;
  }
  
  public int getId() {
    return id;
  }

  public void setId(int id) {
      this.id = id;
  }
  
  public LocalDateTime getStartTimestamp() {
    return startTimestamp;
}

  public void setStartTimestamp(LocalDateTime startTimestamp) {
      this.startTimestamp = startTimestamp;
  }
  
  public LocalDateTime getEndTimestamp() {
      return endTimestamp;
  }
  
  public void setEndTimestamp(LocalDateTime endTimestamp) {
      this.endTimestamp = endTimestamp;
  }
  
  public int getSpecialdayID() {
      return id;
  }
  
  public void setSpecialDayID(int id) {
      this.id = id;
  }
}
