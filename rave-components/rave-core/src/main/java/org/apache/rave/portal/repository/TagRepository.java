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

package org.apache.rave.portal.repository;

import org.apache.rave.repository.Repository;
import org.apache.rave.model.Tag;

import java.util.List;

/**
 * Repository interface for {@link org.apache.rave.model.Tag}
 */
public interface TagRepository extends Repository<Tag> {
    /**
     * @return a List of all {@link org.apache.rave.model.Tag}'s.
     */

    List<Tag> getAll();


    /**
     * @return the total number of tags in the repository
     */
    int getCountAll();

    /**
     * @return the tag matching the keyword
     */

    Tag getByKeyword(String keyword);

}
