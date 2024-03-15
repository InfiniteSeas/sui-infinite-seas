// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.dddml.suiinfinitesea.domain.experiencetable;

import java.util.*;
import org.dddml.suiinfinitesea.domain.*;
import java.util.Date;
import java.math.BigInteger;

public class ExperienceTableCommands {
    private ExperienceTableCommands() {
    }

    public static class __Init__ extends AbstractExperienceTableCommand implements ExperienceTableCommand {

        public String getCommandType() {
            return "__Init__";
        }

        public void setCommandType(String commandType) {
            //do nothing
        }

        /**
         * Id
         */
        private String id;

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        /**
         * Off Chain Version
         */
        private Long offChainVersion;

        public Long getOffChainVersion() {
            return this.offChainVersion;
        }

        public void setOffChainVersion(Long offChainVersion) {
            this.offChainVersion = offChainVersion;
        }

    }

    public static class AddLevel extends AbstractExperienceTableCommand implements ExperienceTableCommand {

        public String getCommandType() {
            return "AddLevel";
        }

        public void setCommandType(String commandType) {
            //do nothing
        }

        /**
         * Id
         */
        private String id;

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        /**
         * Level
         */
        private Integer level;

        public Integer getLevel() {
            return this.level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        /**
         * Experience
         */
        private Long experience;

        public Long getExperience() {
            return this.experience;
        }

        public void setExperience(Long experience) {
            this.experience = experience;
        }

        /**
         * Difference
         */
        private Long difference;

        public Long getDifference() {
            return this.difference;
        }

        public void setDifference(Long difference) {
            this.difference = difference;
        }

        /**
         * Off Chain Version
         */
        private Long offChainVersion;

        public Long getOffChainVersion() {
            return this.offChainVersion;
        }

        public void setOffChainVersion(Long offChainVersion) {
            this.offChainVersion = offChainVersion;
        }

    }

    public static class UpdateLevel extends AbstractExperienceTableCommand implements ExperienceTableCommand {

        public String getCommandType() {
            return "UpdateLevel";
        }

        public void setCommandType(String commandType) {
            //do nothing
        }

        /**
         * Id
         */
        private String id;

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        /**
         * Level
         */
        private Integer level;

        public Integer getLevel() {
            return this.level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        /**
         * Experience
         */
        private Long experience;

        public Long getExperience() {
            return this.experience;
        }

        public void setExperience(Long experience) {
            this.experience = experience;
        }

        /**
         * Difference
         */
        private Long difference;

        public Long getDifference() {
            return this.difference;
        }

        public void setDifference(Long difference) {
            this.difference = difference;
        }

        /**
         * Off Chain Version
         */
        private Long offChainVersion;

        public Long getOffChainVersion() {
            return this.offChainVersion;
        }

        public void setOffChainVersion(Long offChainVersion) {
            this.offChainVersion = offChainVersion;
        }

    }

}
