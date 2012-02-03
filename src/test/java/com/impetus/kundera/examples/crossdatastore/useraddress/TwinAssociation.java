/*******************************************************************************
 * * Copyright 2011 Impetus Infotech.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 ******************************************************************************/
package com.impetus.kundera.examples.crossdatastore.useraddress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class TwinAssociation.
 *
 * @author vivek.mishra
 */
public abstract class TwinAssociation extends AssociationBase
{

    /** The combinations. */
    protected static List<Map<Class, String>> combinations =new ArrayList<Map<Class, String>>();

    /**
     * Inits the.
     *
     * @param classes the classes
     * @param persistenceUnits the persistence units
     */
    public static void init(List<Class> classes, String...persistenceUnits)
    {
        // list of PUS with class.
        Map<Class, String> puClazzMapper = null;

        for(String pu : persistenceUnits)
        {
            for (String p : persistenceUnits)
            {
                puClazzMapper = new HashMap<Class, String>();
                puClazzMapper.put(classes.get(0), pu);
                
                for(Class c : classes.subList(1, classes.size()))
                {
                    puClazzMapper.put(c, p);
                }
                combinations.add(puClazzMapper);
            }
        }
    }
    

    /**
     * Try operation.
     */
    protected void tryOperation()
    {
        for(Map<Class,String> c: combinations)
        {
            switchPersistenceUnits(c);
            insert();
            find();
        }
    }


    /**
     * All unit test cases must implement it. 
     */
    protected abstract void find();

    /**
     * All unit test cases must implement it.
     */
    protected abstract void insert();

    
    
    
}
