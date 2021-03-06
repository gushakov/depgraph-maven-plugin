package com.github.ferstl.depgraph.dependency;

import com.github.ferstl.depgraph.dependency.style.StyleConfiguration;
import org.junit.Before;
import org.junit.Test;

import static com.github.ferstl.depgraph.dependency.DependencyNodeUtil.createDependencyNode;
import static com.github.ferstl.depgraph.dependency.DependencyNodeUtil.createDependencyNodeWithConflict;
import static org.junit.Assert.assertEquals;

public class DotDependencyEdgeRendererTest {

  private StyleConfiguration styleConfiguration;

  @Before
  public void before() {
    this.styleConfiguration = new StyleConfiguration();
  }

  @Test
  public void renderWithoutVersion() {
    // arrange
    DotDependencyEdgeRenderer renderer = new DotDependencyEdgeRenderer(false, this.styleConfiguration);
    DependencyNode from = createDependencyNode("group1", "artifact1", "version1");
    DependencyNode to = createDependencyNode("group2", "artifact2", "version2");

    // act
    String result = renderer.render(from, to);

    // assert
    assertEquals("", result);
  }

  @Test
  public void renderWithNonConflictingVersion() {
    // arrange
    DotDependencyEdgeRenderer renderer = new DotDependencyEdgeRenderer(true, this.styleConfiguration);
    DependencyNode from = createDependencyNode("group1", "artifact1", "version1");
    DependencyNode to = createDependencyNode("group2", "artifact2", "version2");

    // act
    String result = renderer.render(from, to);

    // assert
    assertEquals("", result);
  }

  @Test
  public void renderWithConflictingVersion() {
    // arrange
    DotDependencyEdgeRenderer renderer = new DotDependencyEdgeRenderer(true, this.styleConfiguration);
    DependencyNode from = createDependencyNode("group1", "artifact1", "version1");
    DependencyNode to = createDependencyNodeWithConflict("group2", "artifact2", "version2");

    // act
    String result = renderer.render(from, to);

    // assert
    assertEquals("[label=\"version2\"]", result);
  }
}
