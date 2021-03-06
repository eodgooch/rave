/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.rave.portal.model;

import com.google.common.collect.Lists;
import org.apache.rave.persistence.BasicEntity;
import org.apache.rave.portal.model.conversion.ConvertingListProxyFactory;
import org.apache.rave.portal.model.conversion.JpaConverter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a group in the social database. The assumption in this object is that groups are
 * associated with individuals and are used by those individuals to manage people.
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "groups")
@NamedQueries(
        @NamedQuery(name = JpaGroup.FIND_BY_TITLE, query="select g from JpaGroup g where g.title = :groupId")
)
public class JpaGroup implements BasicEntity, Group {

    public static final String FIND_BY_TITLE = "Group.findById";
    public static final String GROUP_ID_PARAM = "groupId";

    /**
     * The internal object ID used for references to this object. Should be generated by the
     * underlying storage mechanism
     */
    @Id
    @Column(name = "entity_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "groupIdGenerator")
    @TableGenerator(name = "groupIdGenerator", table = "RAVE_PORTAL_SEQUENCES", pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_COUNT", pkColumnValue = "groups", allocationSize = 1, initialValue = 1)
    private Long entityId;

    @Basic
    @Column(name = "title", unique = true)
    private String title;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "owner_id")
    private String owner;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "group_members", joinColumns = @JoinColumn(name="group_id"))
    @Column(name= "person_Id")
    private List<String> members;

    public String getOwnerId() {
        return owner;
    }

    public void setOwnerId(String ownerId) {
        this.owner = ownerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getMemberIds() {
        return members;
    }

    public void setMemberIds(List<String> members) {
        if(this.members == null) {
            this.members = Lists.<String>newLinkedList();
        }
        this.getMemberIds().clear();
        if(members != null) {
            this.getMemberIds().addAll(members);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title  = title;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

}
