package com.codesquad.todo8.model.api.response;

import com.codesquad.todo8.model.Activity;
import com.codesquad.todo8.model.Category;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BoardResponse {

  private List<Category> category;

  private List<Activity> activity;

  private BoardResponse(List<Category> category, List<Activity> activity) {
    this.category = category;
    this.activity = activity;
  }

  public static BoardResponse of(List<Category> category, List<Activity> activity) {
    return new BoardResponse(category, activity);
  }

  public List<Category> getCategory() {
    return category;
  }

  public BoardResponse setCategory(List<Category> category) {
    this.category = category;
    return this;
  }

  public List<Activity> getActivity() {
    return activity;
  }

  public BoardResponse setActivity(List<Activity> activity) {
    this.activity = activity;
    return this;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("category", category)
        .append("activity", activity)
        .toString();
  }
}
