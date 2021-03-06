/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.workbench.screens.guided.dtable.client.widget.analysis.checks;

import com.google.gwt.safehtml.shared.SafeHtml;
import org.drools.workbench.screens.guided.dtable.client.resources.i18n.AnalysisConstants;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.cache.inspectors.PatternInspector;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.cache.inspectors.RuleInspector;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.checks.base.SingleCheck;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.checks.util.Conflict;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.reporting.Explanation;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.reporting.ExplanationProvider;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.reporting.Issue;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.reporting.Severity;

import static org.drools.workbench.screens.guided.dtable.client.widget.analysis.checks.util.HumanReadable.*;

public class DetectMultipleValuesForOneActionCheck
        extends SingleCheck {

    private Conflict conflict = Conflict.EMPTY;

    public DetectMultipleValuesForOneActionCheck( final RuleInspector ruleInspector ) {
        super( ruleInspector );
    }

    @Override
    public void check() {

        conflict = Conflict.EMPTY;

        for ( final PatternInspector patternInspector : ruleInspector.getPatternsInspector() ) {
            final Conflict result = patternInspector.getActionsInspector().hasConflicts();
            if ( result.foundIssue() ) {
                hasIssues = true;
                conflict = result;
                return;

            }
        }

        hasIssues = false;
    }

    @Override
    public Issue getIssue() {
        final Issue issue = new Issue( Severity.WARNING,
                                       AnalysisConstants.INSTANCE.MultipleValuesForOneAction(),
                                       new ExplanationProvider() {
                                           @Override
                                           public SafeHtml toHTML() {
                                               return new Explanation()
                                                       .startNote()
                                                       .addParagraph( AnalysisConstants.INSTANCE.MultipleValuesNote1P1( toHumanReadableString( conflict.getConflictedItem() ), toHumanReadableString( conflict.getConflictingItem() ) ) )
                                                       .end()
                                                       .addParagraph( AnalysisConstants.INSTANCE.MultipleValuesP1() )
                                                       .toHTML();
                                           }
                                       },
                                       ruleInspector );

        return issue;
    }
}
