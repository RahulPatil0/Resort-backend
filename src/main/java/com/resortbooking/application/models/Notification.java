package com.resortbooking.application.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "notifications")
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String message;

	@Column(nullable = false)
	private String type; // e.g., BOOKING, PROMOTION, ALERT

	@Column(nullable = false)
	private boolean read = false;

	@Column(name = "timestamp", nullable = false)
	private LocalDateTime timestamp = LocalDateTime.now();

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, message, read, timestamp, type, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notification other = (Notification) obj;
		return Objects.equals(id, other.id) && Objects.equals(message, other.message) && read == other.read
				&& Objects.equals(timestamp, other.timestamp) && Objects.equals(type, other.type)
				&& Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", message=" + message + ", type=" + type + ", read=" + read + ", timestamp="
				+ timestamp + ", user=" + user + "]";
	}
	
	

}
