package com.unifiedweb360.modal.user;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author SKJHA
 *
 */

@Entity
@Table(name = "PersonalRole")
public class Role {
    @Id
//   @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ROLE_MASTER_SEQ")
    @SequenceGenerator(name="ROLE_MASTER_SEQ", sequenceName="ROLE_MASTER_SEQ",allocationSize=1)
 	  
    private Long id;

    private String name;

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	

    
	

}
