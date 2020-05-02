/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.swift.rule.bestpractices;

import java.util.List;

import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.lang.swift.AbstractSwiftRule;
import net.sourceforge.pmd.lang.swift.ast.SwiftBaseVisitor;
import net.sourceforge.pmd.lang.swift.ast.SwiftParser;
import net.sourceforge.pmd.lang.swift.ast.SwiftParser.FunctionDeclarationContext;
import net.sourceforge.pmd.lang.swift.ast.SwiftParser.InitializerDeclarationContext;

public class UnavailableFunctionRule extends AbstractSwiftRule {

    private static final String AVAILABLE_UNAVAILABLE = "@available(*,unavailable)";
    private static final String FATAL_ERROR = "fatalError";

    public UnavailableFunctionRule() {
        super();
        addRuleChainVisit(FunctionDeclarationContext.class);
        addRuleChainVisit(InitializerDeclarationContext.class);
    }

    @Override
    public SwiftBaseVisitor<Void> buildVisitor(RuleContext ruleCtx) {
        return new SwiftBaseVisitor<Void>() {

            @Override
            public Void visitFunctionDeclaration(final FunctionDeclarationContext ctx) {
                if (ctx == null) {
                    return null;
                }

                if (shouldIncludeUnavailableModifier(ctx.functionBody().codeBlock())) {
                    final SwiftParser.AttributesContext attributes = ctx.functionHead().attributes();
                    if (attributes == null || !hasUnavailableModifier(attributes.attribute())) {
                        addViolation(ruleCtx, ctx);
                    }
                }

                return null;
            }

            @Override
            public Void visitInitializerDeclaration(final InitializerDeclarationContext ctx) {
                if (ctx == null) {
                    return null;
                }

                if (shouldIncludeUnavailableModifier(ctx.initializerBody().codeBlock())) {
                    final SwiftParser.AttributesContext attributes = ctx.initializerHead().attributes();
                    if (attributes == null || !hasUnavailableModifier(attributes.attribute())) {
                        addViolation(ruleCtx, ctx);
                    }
                }

                return null;
            }

            private boolean shouldIncludeUnavailableModifier(final SwiftParser.CodeBlockContext ctx) {
                if (ctx == null || ctx.statements() == null) {
                    return false;
                }

                final List<SwiftParser.StatementContext> statements = ctx.statements().statement();

                return false;
//                return statements.size() == 1 && FATAL_ERROR.equals(statements.get(0).getFirstToken().getText());
            }

            private boolean hasUnavailableModifier(final List<SwiftParser.AttributeContext> attributes) {
                return false;
//                return attributes.stream().anyMatch(atr -> AVAILABLE_UNAVAILABLE.equals(atr.getText()));
            }
        };
    }

}
