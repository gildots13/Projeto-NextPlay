package br.com.nextplay.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TmdbSerieResultDTO {

    private long id;
    private String name;
    private String overview;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("first_air_date")
    private String firstAirDate;
    
    @JsonProperty("genre_ids")
    private List<Integer> genreIds;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getOverview() { return overview; }
    public void setOverview(String overview) { this.overview = overview; }
    public String getPosterPath() { return posterPath; }
    public void setPosterPath(String posterPath) { this.posterPath = posterPath; }
    public String getFirstAirDate() { return firstAirDate; }
    public void setFirstAirDate(String firstAirDate) { this.firstAirDate = firstAirDate; }
    public List<Integer> getGenreIds() { return genreIds; }
    public void setGenreIds(List<Integer> genreIds) { this.genreIds = genreIds; }
}
