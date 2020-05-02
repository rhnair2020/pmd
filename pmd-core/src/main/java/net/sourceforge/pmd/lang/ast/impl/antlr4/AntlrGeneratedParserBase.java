/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.ast.impl.antlr4;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.TerminalNode;

import net.sourceforge.pmd.lang.ast.impl.GenericNode;

/**
 * This is the base class for antlr generated parsers. The implementation
 * of PMD's {@link net.sourceforge.pmd.lang.Parser} interface is {@link AntlrBaseParser}.
 */
public abstract class AntlrGeneratedParserBase<N extends GenericNode<N>> extends Parser {

    public AntlrGeneratedParserBase(TokenStream input) {
        super(input);
    }


    @Override
    public TerminalNode createTerminalNode(ParserRuleContext parent, Token t) {
        return createPmdTerminal(parent, t).asAntlrNode();
    }

    public abstract BaseAntlrTerminalNode<N> createPmdTerminal(ParserRuleContext parent, Token t);

    protected void enterRule(BaseAntlrInnerNode<N> ptree, int state, int alt) {
        enterRule(ptree.asAntlrNode(), state, alt);
    }

    public void enterOuterAlt(BaseAntlrInnerNode<N> localctx, int altNum) {
        enterOuterAlt(localctx.asAntlrNode(), altNum);
    }


    public void pushNewRecursionContext(BaseAntlrInnerNode<N> localctx, int state, int ruleIndex) {
        pushNewRecursionContext(localctx.asAntlrNode(), state, ruleIndex);
    }

    public void enterRecursionRule(BaseAntlrInnerNode<N> localctx, int state, int ruleIndex, int precedence) {
        enterRecursionRule(localctx.asAntlrNode(), state, ruleIndex, precedence);

    }

    public boolean sempred(BaseAntlrInnerNode<N> _localctx, int ruleIndex, int predIndex) {
        return sempred(_localctx.asAntlrNode(), ruleIndex, predIndex);
    }

}
