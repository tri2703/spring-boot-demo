package ute.tri.entity;
import java.io.Serializable;
import java.util.Set;
import jakarta. persistence .*;
import lombok .*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Categories")
public class CategoryEntity implements Serializable {
private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long categoryId;
@Column(name = "category_name", length = 200, columnDefinition = "nvarchar(200) not null")
private String name;

@OneToMany (mappedBy = "category", cascade = CascadeType.ALL )
private Set<ProductEntity> products;

public Object getName() {
	// TODO Auto-generated method stub
	return null;
}

public Object getCategoryId() {
	// TODO Auto-generated method stub
	return null;
}

public void setName(Object name2) {
	// TODO Auto-generated method stub
	
}
}