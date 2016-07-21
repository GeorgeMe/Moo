package com.is.moo.entities;

/**
 * Created by George on 2015/8/13.
 */
public class ConfigurationResponse {

    private ConfigurationImages images;
    private String [] change_keys;

    public ConfigurationImages getImages() {

        return images;
    }

    public class ConfigurationImages {

        private String base_url;
        private String secure_base_url;
        private String [] backdrop_sizes;
        private String [] logo_sizes;
        private String [] poster_sizes;
        private String [] profile_sizes;
        private String [] still_sizes;

        public String getBase_url() {

            return base_url;
        }

        public String getSecure_base_url() {

            return secure_base_url;
        }

        public String[] getBackdrop_sizes() {

            return backdrop_sizes;
        }

        public String[] getLogo_sizes() {

            return logo_sizes;
        }

        public String[] getPoster_sizes() {

            return poster_sizes;
        }

        public String[] getProfile_sizes() {

            return profile_sizes;
        }

        public String[] getStill_sizes() {

            return still_sizes;
        }
    }
}


