package com.lintjar.example;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.ApiKt;
import com.android.tools.lint.detector.api.Issue;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * @author:無忌
 * @date:2021/1/7
 * @description:
 */
public class CustomIssueRegistry extends IssueRegistry {
    @Override
    public int getApi() {
        return ApiKt.CURRENT_API;
    }

    @NotNull
    @Override
    public List<Issue> getIssues() {
        return Arrays.asList(
                LogDetector.ISSUE,
                NewThreadDetector.ISSUE
        );
    }
}
