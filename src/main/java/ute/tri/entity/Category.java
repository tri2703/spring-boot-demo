package ute.tri.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "category")
public class Category implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoryid")
	private Long id;
	
	@Column(name = "categoryname", columnDefinition = "nvarchar(50)")
	@NotEmpty(message = "Không được bỏ trống")
	private String name;
	@Column(name = "images", columnDefinition = "nvarchar(500)")
	private String images;
	
	private int status;
    

}
