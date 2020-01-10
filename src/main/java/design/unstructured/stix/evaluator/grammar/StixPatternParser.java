/*
 * stix-pattern-evaluator
 * Copyright (C) 2020 - Christopher Carver
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package design.unstructured.stix.evaluator.grammar;

import java.util.List;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.*;

@SuppressWarnings({ "all", "warnings", "unchecked", "unused", "cast" })
public class StixPatternParser extends Parser {

    static {
        RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
    public static final int IntNegLiteral = 1, IntPosLiteral = 2, FloatNegLiteral = 3, FloatPosLiteral = 4,
            HexLiteral = 5, BinaryLiteral = 6, StringLiteral = 7, BoolLiteral = 8, TimestampLiteral = 9, AND = 10,
            OR = 11, NOT = 12, FOLLOWEDBY = 13, LIKE = 14, MATCHES = 15, ISSUPERSET = 16, ISSUBSET = 17, EXISTS = 18,
            LAST = 19, IN = 20, START = 21, STOP = 22, SECONDS = 23, TRUE = 24, FALSE = 25, WITHIN = 26, REPEATS = 27,
            TIMES = 28, IdentifierWithoutHyphen = 29, IdentifierWithHyphen = 30, EQ = 31, NEQ = 32, LT = 33, LE = 34,
            GT = 35, GE = 36, QUOTE = 37, COLON = 38, DOT = 39, COMMA = 40, RPAREN = 41, LPAREN = 42, RBRACK = 43,
            LBRACK = 44, PLUS = 45, HYPHEN = 46, MINUS = 47, POWER_OP = 48, DIVIDE = 49, ASTERISK = 50, WS = 51,
            COMMENT = 52, LINE_COMMENT = 53, InvalidCharacter = 54;
    public static final int RULE_pattern = 0, RULE_observationExpressions = 1, RULE_observationExpressionOr = 2,
            RULE_observationExpressionAnd = 3, RULE_observationExpression = 4, RULE_comparisonExpression = 5,
            RULE_comparisonExpressionAnd = 6, RULE_propTest = 7, RULE_orderingComparator = 8, RULE_stringLiteral = 9,
            RULE_startStopQualifier = 10, RULE_withinQualifier = 11, RULE_repeatedQualifier = 12, RULE_objectPath = 13,
            RULE_objectType = 14, RULE_firstPathComponent = 15, RULE_objectPathComponent = 16, RULE_setLiteral = 17,
            RULE_primitiveLiteral = 18, RULE_orderableLiteral = 19;

    private static String[] makeRuleNames() {
        return new String[] { "pattern", "observationExpressions", "observationExpressionOr",
                "observationExpressionAnd", "observationExpression", "comparisonExpression", "comparisonExpressionAnd",
                "propTest", "orderingComparator", "stringLiteral", "startStopQualifier", "withinQualifier",
                "repeatedQualifier", "objectPath", "objectType", "firstPathComponent", "objectPathComponent",
                "setLiteral", "primitiveLiteral", "orderableLiteral" };
    }

    public static final String[] ruleNames = makeRuleNames();

    private static String[] makeLiteralNames() {
        return new String[] { null, null, null, null, null, null, null, null, null, null, "'AND'", "'OR'", "'NOT'",
                "'FOLLOWEDBY'", "'LIKE'", "'MATCHES'", "'ISSUPERSET'", "'ISSUBSET'", "'EXISTS'", "'LAST'", "'IN'",
                "'START'", "'STOP'", "'SECONDS'", "'true'", "'false'", "'WITHIN'", "'REPEATS'", "'TIMES'", null, null,
                null, null, "'<'", "'<='", "'>'", "'>='", "'''", "':'", "'.'", "','", "')'", "'('", "']'", "'['", "'+'",
                null, "'-'", "'^'", "'/'", "'*'" };
    }

    private static final String[] _LITERAL_NAMES = makeLiteralNames();

    private static String[] makeSymbolicNames() {
        return new String[] { null, "IntNegLiteral", "IntPosLiteral", "FloatNegLiteral", "FloatPosLiteral",
                "HexLiteral", "BinaryLiteral", "StringLiteral", "BoolLiteral", "TimestampLiteral", "AND", "OR", "NOT",
                "FOLLOWEDBY", "LIKE", "MATCHES", "ISSUPERSET", "ISSUBSET", "EXISTS", "LAST", "IN", "START", "STOP",
                "SECONDS", "TRUE", "FALSE", "WITHIN", "REPEATS", "TIMES", "IdentifierWithoutHyphen",
                "IdentifierWithHyphen", "EQ", "NEQ", "LT", "LE", "GT", "GE", "QUOTE", "COLON", "DOT", "COMMA", "RPAREN",
                "LPAREN", "RBRACK", "LBRACK", "PLUS", "HYPHEN", "MINUS", "POWER_OP", "DIVIDE", "ASTERISK", "WS",
                "COMMENT", "LINE_COMMENT", "InvalidCharacter" };
    }

    private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;

    static {
        tokenNames = new String[_SYMBOLIC_NAMES.length];
        for (int i = 0; i < tokenNames.length; i++) {
            tokenNames[i] = VOCABULARY.getLiteralName(i);
            if (tokenNames[i] == null) {
                tokenNames[i] = VOCABULARY.getSymbolicName(i);
            }

            if (tokenNames[i] == null) {
                tokenNames[i] = "<INVALID>";
            }
        }
    }

    @Override
    @Deprecated
    public String[] getTokenNames() {
        return tokenNames;
    }

    @Override

    public Vocabulary getVocabulary() {
        return VOCABULARY;
    }

    @Override
    public String getGrammarFileName() {
        return "StixPattern.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public StixPatternParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    public static class PatternContext extends ParserRuleContext {

        public ObservationExpressionsContext observationExpressions() {
            return getRuleContext(ObservationExpressionsContext.class, 0);
        }

        public TerminalNode EOF() {
            return getToken(StixPatternParser.EOF, 0);
        }

        public PatternContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_pattern;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterPattern(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitPattern(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitPattern(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public final PatternContext pattern() throws RecognitionException {
        PatternContext _localctx = new PatternContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_pattern);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(40);
                observationExpressions(0);
                setState(41);
                match(EOF);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ObservationExpressionsContext extends ParserRuleContext {

        public ObservationExpressionOrContext observationExpressionOr() {
            return getRuleContext(ObservationExpressionOrContext.class, 0);
        }

        public List<ObservationExpressionsContext> observationExpressions() {
            return getRuleContexts(ObservationExpressionsContext.class);
        }

        public ObservationExpressionsContext observationExpressions(int i) {
            return getRuleContext(ObservationExpressionsContext.class, i);
        }

        public TerminalNode FOLLOWEDBY() {
            return getToken(StixPatternParser.FOLLOWEDBY, 0);
        }

        public ObservationExpressionsContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_observationExpressions;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterObservationExpressions(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitObservationExpressions(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitObservationExpressions(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public final ObservationExpressionsContext observationExpressions() throws RecognitionException {
        return observationExpressions(0);
    }

    private ObservationExpressionsContext observationExpressions(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = _ctx;
        int _parentState = getState();
        ObservationExpressionsContext _localctx = new ObservationExpressionsContext(_ctx, _parentState);
        ObservationExpressionsContext _prevctx = _localctx;
        int _startState = 2;
        enterRecursionRule(_localctx, 2, RULE_observationExpressions, _p);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                {
                    setState(44);
                    observationExpressionOr(0);
                }
                _ctx.stop = _input.LT(-1);
                setState(51);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 0, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) {
                            triggerExitRuleEvent();
                        }
                        _prevctx = _localctx;
                        {
                            {
                                _localctx = new ObservationExpressionsContext(_parentctx, _parentState);
                                pushNewRecursionContext(_localctx, _startState, RULE_observationExpressions);
                                setState(46);
                                if (!(precpred(_ctx, 2))) {
                                    throw new FailedPredicateException(this, "precpred(_ctx, 2)");
                                }
                                setState(47);
                                match(FOLLOWEDBY);
                                setState(48);
                                observationExpressions(3);
                            }
                        }
                    }
                    setState(53);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 0, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            unrollRecursionContexts(_parentctx);
        }
        return _localctx;
    }

    public static class ObservationExpressionOrContext extends ParserRuleContext {

        public ObservationExpressionAndContext observationExpressionAnd() {
            return getRuleContext(ObservationExpressionAndContext.class, 0);
        }

        public List<ObservationExpressionOrContext> observationExpressionOr() {
            return getRuleContexts(ObservationExpressionOrContext.class);
        }

        public ObservationExpressionOrContext observationExpressionOr(int i) {
            return getRuleContext(ObservationExpressionOrContext.class, i);
        }

        public TerminalNode OR() {
            return getToken(StixPatternParser.OR, 0);
        }

        public ObservationExpressionOrContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_observationExpressionOr;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterObservationExpressionOr(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitObservationExpressionOr(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitObservationExpressionOr(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public final ObservationExpressionOrContext observationExpressionOr() throws RecognitionException {
        return observationExpressionOr(0);
    }

    private ObservationExpressionOrContext observationExpressionOr(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = _ctx;
        int _parentState = getState();
        ObservationExpressionOrContext _localctx = new ObservationExpressionOrContext(_ctx, _parentState);
        ObservationExpressionOrContext _prevctx = _localctx;
        int _startState = 4;
        enterRecursionRule(_localctx, 4, RULE_observationExpressionOr, _p);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                {
                    setState(55);
                    observationExpressionAnd(0);
                }
                _ctx.stop = _input.LT(-1);
                setState(62);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 1, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) {
                            triggerExitRuleEvent();
                        }
                        _prevctx = _localctx;
                        {
                            {
                                _localctx = new ObservationExpressionOrContext(_parentctx, _parentState);
                                pushNewRecursionContext(_localctx, _startState, RULE_observationExpressionOr);
                                setState(57);
                                if (!(precpred(_ctx, 2))) {
                                    throw new FailedPredicateException(this, "precpred(_ctx, 2)");
                                }
                                setState(58);
                                match(OR);
                                setState(59);
                                observationExpressionOr(3);
                            }
                        }
                    }
                    setState(64);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 1, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            unrollRecursionContexts(_parentctx);
        }
        return _localctx;
    }

    public static class ObservationExpressionAndContext extends ParserRuleContext {

        public ObservationExpressionContext observationExpression() {
            return getRuleContext(ObservationExpressionContext.class, 0);
        }

        public List<ObservationExpressionAndContext> observationExpressionAnd() {
            return getRuleContexts(ObservationExpressionAndContext.class);
        }

        public ObservationExpressionAndContext observationExpressionAnd(int i) {
            return getRuleContext(ObservationExpressionAndContext.class, i);
        }

        public TerminalNode AND() {
            return getToken(StixPatternParser.AND, 0);
        }

        public ObservationExpressionAndContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_observationExpressionAnd;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterObservationExpressionAnd(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitObservationExpressionAnd(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitObservationExpressionAnd(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public final ObservationExpressionAndContext observationExpressionAnd() throws RecognitionException {
        return observationExpressionAnd(0);
    }

    private ObservationExpressionAndContext observationExpressionAnd(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = _ctx;
        int _parentState = getState();
        ObservationExpressionAndContext _localctx = new ObservationExpressionAndContext(_ctx, _parentState);
        ObservationExpressionAndContext _prevctx = _localctx;
        int _startState = 6;
        enterRecursionRule(_localctx, 6, RULE_observationExpressionAnd, _p);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                {
                    setState(66);
                    observationExpression(0);
                }
                _ctx.stop = _input.LT(-1);
                setState(73);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 2, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) {
                            triggerExitRuleEvent();
                        }
                        _prevctx = _localctx;
                        {
                            {
                                _localctx = new ObservationExpressionAndContext(_parentctx, _parentState);
                                pushNewRecursionContext(_localctx, _startState, RULE_observationExpressionAnd);
                                setState(68);
                                if (!(precpred(_ctx, 2))) {
                                    throw new FailedPredicateException(this, "precpred(_ctx, 2)");
                                }
                                setState(69);
                                match(AND);
                                setState(70);
                                observationExpressionAnd(3);
                            }
                        }
                    }
                    setState(75);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 2, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            unrollRecursionContexts(_parentctx);
        }
        return _localctx;
    }

    public static class ObservationExpressionContext extends ParserRuleContext {

        public ObservationExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_observationExpression;
        }

        public ObservationExpressionContext() {
        }

        public void copyFrom(ObservationExpressionContext ctx) {
            super.copyFrom(ctx);
        }
    }

    public static class ObservationExpressionRepeatedContext extends ObservationExpressionContext {

        public ObservationExpressionContext observationExpression() {
            return getRuleContext(ObservationExpressionContext.class, 0);
        }

        public RepeatedQualifierContext repeatedQualifier() {
            return getRuleContext(RepeatedQualifierContext.class, 0);
        }

        public ObservationExpressionRepeatedContext(ObservationExpressionContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterObservationExpressionRepeated(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitObservationExpressionRepeated(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitObservationExpressionRepeated(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public static class ObservationExpressionSimpleContext extends ObservationExpressionContext {

        public TerminalNode LBRACK() {
            return getToken(StixPatternParser.LBRACK, 0);
        }

        public ComparisonExpressionContext comparisonExpression() {
            return getRuleContext(ComparisonExpressionContext.class, 0);
        }

        public TerminalNode RBRACK() {
            return getToken(StixPatternParser.RBRACK, 0);
        }

        public ObservationExpressionSimpleContext(ObservationExpressionContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterObservationExpressionSimple(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitObservationExpressionSimple(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitObservationExpressionSimple(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public static class ObservationExpressionCompoundContext extends ObservationExpressionContext {

        public TerminalNode LPAREN() {
            return getToken(StixPatternParser.LPAREN, 0);
        }

        public ObservationExpressionsContext observationExpressions() {
            return getRuleContext(ObservationExpressionsContext.class, 0);
        }

        public TerminalNode RPAREN() {
            return getToken(StixPatternParser.RPAREN, 0);
        }

        public ObservationExpressionCompoundContext(ObservationExpressionContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterObservationExpressionCompound(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitObservationExpressionCompound(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitObservationExpressionCompound(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public static class ObservationExpressionWithinContext extends ObservationExpressionContext {

        public ObservationExpressionContext observationExpression() {
            return getRuleContext(ObservationExpressionContext.class, 0);
        }

        public WithinQualifierContext withinQualifier() {
            return getRuleContext(WithinQualifierContext.class, 0);
        }

        public ObservationExpressionWithinContext(ObservationExpressionContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterObservationExpressionWithin(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitObservationExpressionWithin(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitObservationExpressionWithin(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public static class ObservationExpressionStartStopContext extends ObservationExpressionContext {

        public ObservationExpressionContext observationExpression() {
            return getRuleContext(ObservationExpressionContext.class, 0);
        }

        public StartStopQualifierContext startStopQualifier() {
            return getRuleContext(StartStopQualifierContext.class, 0);
        }

        public ObservationExpressionStartStopContext(ObservationExpressionContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterObservationExpressionStartStop(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitObservationExpressionStartStop(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitObservationExpressionStartStop(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public final ObservationExpressionContext observationExpression() throws RecognitionException {
        return observationExpression(0);
    }

    private ObservationExpressionContext observationExpression(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = _ctx;
        int _parentState = getState();
        ObservationExpressionContext _localctx = new ObservationExpressionContext(_ctx, _parentState);
        ObservationExpressionContext _prevctx = _localctx;
        int _startState = 8;
        enterRecursionRule(_localctx, 8, RULE_observationExpression, _p);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(85);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                case LBRACK: {
                    _localctx = new ObservationExpressionSimpleContext(_localctx);
                    _ctx = _localctx;
                    _prevctx = _localctx;

                    setState(77);
                    match(LBRACK);
                    setState(78);
                    comparisonExpression(0);
                    setState(79);
                    match(RBRACK);
                }
                    break;
                case LPAREN: {
                    _localctx = new ObservationExpressionCompoundContext(_localctx);
                    _ctx = _localctx;
                    _prevctx = _localctx;
                    setState(81);
                    match(LPAREN);
                    setState(82);
                    observationExpressions(0);
                    setState(83);
                    match(RPAREN);
                }
                    break;
                default:
                    throw new NoViableAltException(this);
                }
                _ctx.stop = _input.LT(-1);
                setState(95);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 5, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) {
                            triggerExitRuleEvent();
                        }
                        _prevctx = _localctx;
                        {
                            setState(93);
                            _errHandler.sync(this);
                            switch (getInterpreter().adaptivePredict(_input, 4, _ctx)) {
                            case 1: {
                                _localctx = new ObservationExpressionStartStopContext(
                                        new ObservationExpressionContext(_parentctx, _parentState));
                                pushNewRecursionContext(_localctx, _startState, RULE_observationExpression);
                                setState(87);
                                if (!(precpred(_ctx, 3))) {
                                    throw new FailedPredicateException(this, "precpred(_ctx, 3)");
                                }
                                setState(88);
                                startStopQualifier();
                            }
                                break;
                            case 2: {
                                _localctx = new ObservationExpressionWithinContext(
                                        new ObservationExpressionContext(_parentctx, _parentState));
                                pushNewRecursionContext(_localctx, _startState, RULE_observationExpression);
                                setState(89);
                                if (!(precpred(_ctx, 2))) {
                                    throw new FailedPredicateException(this, "precpred(_ctx, 2)");
                                }
                                setState(90);
                                withinQualifier();
                            }
                                break;
                            case 3: {
                                _localctx = new ObservationExpressionRepeatedContext(
                                        new ObservationExpressionContext(_parentctx, _parentState));
                                pushNewRecursionContext(_localctx, _startState, RULE_observationExpression);
                                setState(91);
                                if (!(precpred(_ctx, 1))) {
                                    throw new FailedPredicateException(this, "precpred(_ctx, 1)");
                                }
                                setState(92);
                                repeatedQualifier();
                            }
                                break;
                            }
                        }
                    }
                    setState(97);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 5, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            unrollRecursionContexts(_parentctx);
        }
        return _localctx;
    }

    public static class ComparisonExpressionContext extends ParserRuleContext {

        public ComparisonExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_comparisonExpression;
        }

        public ComparisonExpressionContext() {
        }

        public void copyFrom(ComparisonExpressionContext ctx) {
            super.copyFrom(ctx);
        }
    }

    public static class ComparisonExpressionAnd_Context extends ComparisonExpressionContext {

        public ComparisonExpressionAndContext comparisonExpressionAnd() {
            return getRuleContext(ComparisonExpressionAndContext.class, 0);
        }

        public ComparisonExpressionAnd_Context(ComparisonExpressionContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterComparisonExpressionAnd_(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitComparisonExpressionAnd_(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitComparisonExpressionAnd_(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public static class ComparisonExpressionOredContext extends ComparisonExpressionContext {

        public List<ComparisonExpressionContext> comparisonExpression() {
            return getRuleContexts(ComparisonExpressionContext.class);
        }

        public ComparisonExpressionContext comparisonExpression(int i) {
            return getRuleContext(ComparisonExpressionContext.class, i);
        }

        public TerminalNode OR() {
            return getToken(StixPatternParser.OR, 0);
        }

        public ComparisonExpressionOredContext(ComparisonExpressionContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterComparisonExpressionOred(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitComparisonExpressionOred(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitComparisonExpressionOred(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public final ComparisonExpressionContext comparisonExpression() throws RecognitionException {
        return comparisonExpression(0);
    }

    private ComparisonExpressionContext comparisonExpression(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = _ctx;
        int _parentState = getState();
        ComparisonExpressionContext _localctx = new ComparisonExpressionContext(_ctx, _parentState);
        ComparisonExpressionContext _prevctx = _localctx;
        int _startState = 10;
        enterRecursionRule(_localctx, 10, RULE_comparisonExpression, _p);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                {
                    _localctx = new ComparisonExpressionAnd_Context(_localctx);
                    _ctx = _localctx;
                    _prevctx = _localctx;

                    setState(99);
                    comparisonExpressionAnd(0);
                }
                _ctx.stop = _input.LT(-1);
                setState(106);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 6, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) {
                            triggerExitRuleEvent();
                        }
                        _prevctx = _localctx;
                        {
                            {
                                _localctx = new ComparisonExpressionOredContext(
                                        new ComparisonExpressionContext(_parentctx, _parentState));
                                pushNewRecursionContext(_localctx, _startState, RULE_comparisonExpression);
                                setState(101);
                                if (!(precpred(_ctx, 2))) {
                                    throw new FailedPredicateException(this, "precpred(_ctx, 2)");
                                }
                                setState(102);
                                match(OR);
                                setState(103);
                                comparisonExpression(3);
                            }
                        }
                    }
                    setState(108);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 6, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            unrollRecursionContexts(_parentctx);
        }
        return _localctx;
    }

    public static class ComparisonExpressionAndContext extends ParserRuleContext {

        public ComparisonExpressionAndContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_comparisonExpressionAnd;
        }

        public ComparisonExpressionAndContext() {
        }

        public void copyFrom(ComparisonExpressionAndContext ctx) {
            super.copyFrom(ctx);
        }
    }

    public static class ComparisonExpressionAndPropTestContext extends ComparisonExpressionAndContext {

        public PropTestContext propTest() {
            return getRuleContext(PropTestContext.class, 0);
        }

        public ComparisonExpressionAndPropTestContext(ComparisonExpressionAndContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterComparisonExpressionAndPropTest(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitComparisonExpressionAndPropTest(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitComparisonExpressionAndPropTest(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public static class ComparisonExpressionAndedContext extends ComparisonExpressionAndContext {

        public List<ComparisonExpressionAndContext> comparisonExpressionAnd() {
            return getRuleContexts(ComparisonExpressionAndContext.class);
        }

        public ComparisonExpressionAndContext comparisonExpressionAnd(int i) {
            return getRuleContext(ComparisonExpressionAndContext.class, i);
        }

        public TerminalNode AND() {
            return getToken(StixPatternParser.AND, 0);
        }

        public ComparisonExpressionAndedContext(ComparisonExpressionAndContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterComparisonExpressionAnded(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitComparisonExpressionAnded(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitComparisonExpressionAnded(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public final ComparisonExpressionAndContext comparisonExpressionAnd() throws RecognitionException {
        return comparisonExpressionAnd(0);
    }

    private ComparisonExpressionAndContext comparisonExpressionAnd(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = _ctx;
        int _parentState = getState();
        ComparisonExpressionAndContext _localctx = new ComparisonExpressionAndContext(_ctx, _parentState);
        ComparisonExpressionAndContext _prevctx = _localctx;
        int _startState = 12;
        enterRecursionRule(_localctx, 12, RULE_comparisonExpressionAnd, _p);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                {
                    _localctx = new ComparisonExpressionAndPropTestContext(_localctx);
                    _ctx = _localctx;
                    _prevctx = _localctx;

                    setState(110);
                    propTest();
                }
                _ctx.stop = _input.LT(-1);
                setState(117);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 7, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) {
                            triggerExitRuleEvent();
                        }
                        _prevctx = _localctx;
                        {
                            {
                                _localctx = new ComparisonExpressionAndedContext(
                                        new ComparisonExpressionAndContext(_parentctx, _parentState));
                                pushNewRecursionContext(_localctx, _startState, RULE_comparisonExpressionAnd);
                                setState(112);
                                if (!(precpred(_ctx, 2))) {
                                    throw new FailedPredicateException(this, "precpred(_ctx, 2)");
                                }
                                setState(113);
                                match(AND);
                                setState(114);
                                comparisonExpressionAnd(3);
                            }
                        }
                    }
                    setState(119);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 7, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            unrollRecursionContexts(_parentctx);
        }
        return _localctx;
    }

    public static class PropTestContext extends ParserRuleContext {

        public PropTestContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_propTest;
        }

        public PropTestContext() {
        }

        public void copyFrom(PropTestContext ctx) {
            super.copyFrom(ctx);
        }
    }

    public static class PropTestExistsContext extends PropTestContext {

        public TerminalNode EXISTS() {
            return getToken(StixPatternParser.EXISTS, 0);
        }

        public ObjectPathContext objectPath() {
            return getRuleContext(ObjectPathContext.class, 0);
        }

        public PropTestExistsContext(PropTestContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterPropTestExists(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitPropTestExists(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitPropTestExists(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public static class PropTestRegexContext extends PropTestContext {

        public ObjectPathContext objectPath() {
            return getRuleContext(ObjectPathContext.class, 0);
        }

        public TerminalNode MATCHES() {
            return getToken(StixPatternParser.MATCHES, 0);
        }

        public StringLiteralContext stringLiteral() {
            return getRuleContext(StringLiteralContext.class, 0);
        }

        public TerminalNode NOT() {
            return getToken(StixPatternParser.NOT, 0);
        }

        public PropTestRegexContext(PropTestContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterPropTestRegex(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitPropTestRegex(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitPropTestRegex(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public static class PropTestOrderContext extends PropTestContext {

        public ObjectPathContext objectPath() {
            return getRuleContext(ObjectPathContext.class, 0);
        }

        public OrderableLiteralContext orderableLiteral() {
            return getRuleContext(OrderableLiteralContext.class, 0);
        }

        public TerminalNode GT() {
            return getToken(StixPatternParser.GT, 0);
        }

        public TerminalNode LT() {
            return getToken(StixPatternParser.LT, 0);
        }

        public TerminalNode GE() {
            return getToken(StixPatternParser.GE, 0);
        }

        public TerminalNode LE() {
            return getToken(StixPatternParser.LE, 0);
        }

        public TerminalNode NOT() {
            return getToken(StixPatternParser.NOT, 0);
        }

        public PropTestOrderContext(PropTestContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterPropTestOrder(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitPropTestOrder(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitPropTestOrder(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public static class PropTestLikeContext extends PropTestContext {

        public ObjectPathContext objectPath() {
            return getRuleContext(ObjectPathContext.class, 0);
        }

        public TerminalNode LIKE() {
            return getToken(StixPatternParser.LIKE, 0);
        }

        public StringLiteralContext stringLiteral() {
            return getRuleContext(StringLiteralContext.class, 0);
        }

        public TerminalNode NOT() {
            return getToken(StixPatternParser.NOT, 0);
        }

        public PropTestLikeContext(PropTestContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterPropTestLike(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitPropTestLike(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitPropTestLike(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public static class PropTestEqualContext extends PropTestContext {

        public ObjectPathContext objectPath() {
            return getRuleContext(ObjectPathContext.class, 0);
        }

        public PrimitiveLiteralContext primitiveLiteral() {
            return getRuleContext(PrimitiveLiteralContext.class, 0);
        }

        public TerminalNode EQ() {
            return getToken(StixPatternParser.EQ, 0);
        }

        public TerminalNode NEQ() {
            return getToken(StixPatternParser.NEQ, 0);
        }

        public TerminalNode NOT() {
            return getToken(StixPatternParser.NOT, 0);
        }

        public PropTestEqualContext(PropTestContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterPropTestEqual(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitPropTestEqual(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitPropTestEqual(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public static class PropTestSetContext extends PropTestContext {

        public ObjectPathContext objectPath() {
            return getRuleContext(ObjectPathContext.class, 0);
        }

        public TerminalNode IN() {
            return getToken(StixPatternParser.IN, 0);
        }

        public SetLiteralContext setLiteral() {
            return getRuleContext(SetLiteralContext.class, 0);
        }

        public TerminalNode NOT() {
            return getToken(StixPatternParser.NOT, 0);
        }

        public PropTestSetContext(PropTestContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterPropTestSet(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitPropTestSet(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitPropTestSet(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public static class PropTestIsSubsetContext extends PropTestContext {

        public ObjectPathContext objectPath() {
            return getRuleContext(ObjectPathContext.class, 0);
        }

        public TerminalNode ISSUBSET() {
            return getToken(StixPatternParser.ISSUBSET, 0);
        }

        public StringLiteralContext stringLiteral() {
            return getRuleContext(StringLiteralContext.class, 0);
        }

        public TerminalNode NOT() {
            return getToken(StixPatternParser.NOT, 0);
        }

        public PropTestIsSubsetContext(PropTestContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterPropTestIsSubset(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitPropTestIsSubset(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitPropTestIsSubset(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public static class PropTestParenContext extends PropTestContext {

        public TerminalNode LPAREN() {
            return getToken(StixPatternParser.LPAREN, 0);
        }

        public ComparisonExpressionContext comparisonExpression() {
            return getRuleContext(ComparisonExpressionContext.class, 0);
        }

        public TerminalNode RPAREN() {
            return getToken(StixPatternParser.RPAREN, 0);
        }

        public PropTestParenContext(PropTestContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterPropTestParen(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitPropTestParen(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitPropTestParen(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public static class PropTestIsSupersetContext extends PropTestContext {

        public ObjectPathContext objectPath() {
            return getRuleContext(ObjectPathContext.class, 0);
        }

        public TerminalNode ISSUPERSET() {
            return getToken(StixPatternParser.ISSUPERSET, 0);
        }

        public StringLiteralContext stringLiteral() {
            return getRuleContext(StringLiteralContext.class, 0);
        }

        public TerminalNode NOT() {
            return getToken(StixPatternParser.NOT, 0);
        }

        public PropTestIsSupersetContext(PropTestContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterPropTestIsSuperset(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitPropTestIsSuperset(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitPropTestIsSuperset(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public final PropTestContext propTest() throws RecognitionException {
        PropTestContext _localctx = new PropTestContext(_ctx, getState());
        enterRule(_localctx, 14, RULE_propTest);
        int _la;
        try {
            setState(175);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 15, _ctx)) {
            case 1:
                _localctx = new PropTestEqualContext(_localctx);
                enterOuterAlt(_localctx, 1); {
                setState(120);
                objectPath();
                setState(122);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == NOT) {
                    {
                        setState(121);
                        match(NOT);
                    }
                }

                setState(124);
                _la = _input.LA(1);
                if (!(_la == EQ || _la == NEQ)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) {
                        matchedEOF = true;
                    }
                    _errHandler.reportMatch(this);
                    consume();
                }
                setState(125);
                primitiveLiteral();
            }
                break;
            case 2:
                _localctx = new PropTestOrderContext(_localctx);
                enterOuterAlt(_localctx, 2); {
                setState(127);
                objectPath();
                setState(129);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == NOT) {
                    {
                        setState(128);
                        match(NOT);
                    }
                }

                setState(131);
                _la = _input.LA(1);
                if (!((((_la) & ~0x3f) == 0
                        && ((1L << _la) & ((1L << LT) | (1L << LE) | (1L << GT) | (1L << GE))) != 0))) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) {
                        matchedEOF = true;
                    }
                    _errHandler.reportMatch(this);
                    consume();
                }
                setState(132);
                orderableLiteral();
            }
                break;
            case 3:
                _localctx = new PropTestSetContext(_localctx);
                enterOuterAlt(_localctx, 3); {
                setState(134);
                objectPath();
                setState(136);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == NOT) {
                    {
                        setState(135);
                        match(NOT);
                    }
                }

                setState(138);
                match(IN);
                setState(139);
                setLiteral();
            }
                break;
            case 4:
                _localctx = new PropTestLikeContext(_localctx);
                enterOuterAlt(_localctx, 4); {
                setState(141);
                objectPath();
                setState(143);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == NOT) {
                    {
                        setState(142);
                        match(NOT);
                    }
                }

                setState(145);
                match(LIKE);
                setState(146);
                stringLiteral();
            }
                break;
            case 5:
                _localctx = new PropTestRegexContext(_localctx);
                enterOuterAlt(_localctx, 5); {
                setState(148);
                objectPath();
                setState(150);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == NOT) {
                    {
                        setState(149);
                        match(NOT);
                    }
                }

                setState(152);
                match(MATCHES);
                setState(153);
                stringLiteral();
            }
                break;
            case 6:
                _localctx = new PropTestIsSubsetContext(_localctx);
                enterOuterAlt(_localctx, 6); {
                setState(155);
                objectPath();
                setState(157);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == NOT) {
                    {
                        setState(156);
                        match(NOT);
                    }
                }

                setState(159);
                match(ISSUBSET);
                setState(160);
                stringLiteral();
            }
                break;
            case 7:
                _localctx = new PropTestIsSupersetContext(_localctx);
                enterOuterAlt(_localctx, 7); {
                setState(162);
                objectPath();
                setState(164);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == NOT) {
                    {
                        setState(163);
                        match(NOT);
                    }
                }

                setState(166);
                match(ISSUPERSET);
                setState(167);
                stringLiteral();
            }
                break;
            case 8:
                _localctx = new PropTestParenContext(_localctx);
                enterOuterAlt(_localctx, 8); {
                setState(169);
                match(LPAREN);
                setState(170);
                comparisonExpression(0);
                setState(171);
                match(RPAREN);
            }
                break;
            case 9:
                _localctx = new PropTestExistsContext(_localctx);
                enterOuterAlt(_localctx, 9); {
                setState(173);
                match(EXISTS);
                setState(174);
                objectPath();
            }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class OrderingComparatorContext extends ParserRuleContext {

        public TerminalNode GT() {
            return getToken(StixPatternParser.GT, 0);
        }

        public TerminalNode LT() {
            return getToken(StixPatternParser.LT, 0);
        }

        public TerminalNode GE() {
            return getToken(StixPatternParser.GE, 0);
        }

        public TerminalNode LE() {
            return getToken(StixPatternParser.LE, 0);
        }

        public OrderingComparatorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_orderingComparator;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterOrderingComparator(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitOrderingComparator(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitOrderingComparator(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public final OrderingComparatorContext orderingComparator() throws RecognitionException {
        OrderingComparatorContext _localctx = new OrderingComparatorContext(_ctx, getState());
        enterRule(_localctx, 16, RULE_orderingComparator);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(177);
                _la = _input.LA(1);
                if (!((((_la) & ~0x3f) == 0
                        && ((1L << _la) & ((1L << LT) | (1L << LE) | (1L << GT) | (1L << GE))) != 0))) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) {
                        matchedEOF = true;
                    }
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class StringLiteralContext extends ParserRuleContext {

        public TerminalNode StringLiteral() {
            return getToken(StixPatternParser.StringLiteral, 0);
        }

        public StringLiteralContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_stringLiteral;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterStringLiteral(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitStringLiteral(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitStringLiteral(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public final StringLiteralContext stringLiteral() throws RecognitionException {
        StringLiteralContext _localctx = new StringLiteralContext(_ctx, getState());
        enterRule(_localctx, 18, RULE_stringLiteral);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(179);
                match(StringLiteral);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class StartStopQualifierContext extends ParserRuleContext {

        public TerminalNode START() {
            return getToken(StixPatternParser.START, 0);
        }

        public List<TerminalNode> TimestampLiteral() {
            return getTokens(StixPatternParser.TimestampLiteral);
        }

        public TerminalNode TimestampLiteral(int i) {
            return getToken(StixPatternParser.TimestampLiteral, i);
        }

        public TerminalNode STOP() {
            return getToken(StixPatternParser.STOP, 0);
        }

        public StartStopQualifierContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_startStopQualifier;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterStartStopQualifier(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitStartStopQualifier(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitStartStopQualifier(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public final StartStopQualifierContext startStopQualifier() throws RecognitionException {
        StartStopQualifierContext _localctx = new StartStopQualifierContext(_ctx, getState());
        enterRule(_localctx, 20, RULE_startStopQualifier);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(181);
                match(START);
                setState(182);
                match(TimestampLiteral);
                setState(183);
                match(STOP);
                setState(184);
                match(TimestampLiteral);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class WithinQualifierContext extends ParserRuleContext {

        public TerminalNode WITHIN() {
            return getToken(StixPatternParser.WITHIN, 0);
        }

        public TerminalNode SECONDS() {
            return getToken(StixPatternParser.SECONDS, 0);
        }

        public TerminalNode IntPosLiteral() {
            return getToken(StixPatternParser.IntPosLiteral, 0);
        }

        public TerminalNode FloatPosLiteral() {
            return getToken(StixPatternParser.FloatPosLiteral, 0);
        }

        public WithinQualifierContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_withinQualifier;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterWithinQualifier(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitWithinQualifier(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitWithinQualifier(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public final WithinQualifierContext withinQualifier() throws RecognitionException {
        WithinQualifierContext _localctx = new WithinQualifierContext(_ctx, getState());
        enterRule(_localctx, 22, RULE_withinQualifier);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(186);
                match(WITHIN);
                setState(187);
                _la = _input.LA(1);
                if (!(_la == IntPosLiteral || _la == FloatPosLiteral)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) {
                        matchedEOF = true;
                    }
                    _errHandler.reportMatch(this);
                    consume();
                }
                setState(188);
                match(SECONDS);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class RepeatedQualifierContext extends ParserRuleContext {

        public TerminalNode REPEATS() {
            return getToken(StixPatternParser.REPEATS, 0);
        }

        public TerminalNode IntPosLiteral() {
            return getToken(StixPatternParser.IntPosLiteral, 0);
        }

        public TerminalNode TIMES() {
            return getToken(StixPatternParser.TIMES, 0);
        }

        public RepeatedQualifierContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_repeatedQualifier;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterRepeatedQualifier(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitRepeatedQualifier(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitRepeatedQualifier(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public final RepeatedQualifierContext repeatedQualifier() throws RecognitionException {
        RepeatedQualifierContext _localctx = new RepeatedQualifierContext(_ctx, getState());
        enterRule(_localctx, 24, RULE_repeatedQualifier);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(190);
                match(REPEATS);
                setState(191);
                match(IntPosLiteral);
                setState(192);
                match(TIMES);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ObjectPathContext extends ParserRuleContext {

        public ObjectTypeContext objectType() {
            return getRuleContext(ObjectTypeContext.class, 0);
        }

        public TerminalNode COLON() {
            return getToken(StixPatternParser.COLON, 0);
        }

        public FirstPathComponentContext firstPathComponent() {
            return getRuleContext(FirstPathComponentContext.class, 0);
        }

        public ObjectPathComponentContext objectPathComponent() {
            return getRuleContext(ObjectPathComponentContext.class, 0);
        }

        public ObjectPathContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_objectPath;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterObjectPath(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitObjectPath(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitObjectPath(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public final ObjectPathContext objectPath() throws RecognitionException {
        ObjectPathContext _localctx = new ObjectPathContext(_ctx, getState());
        enterRule(_localctx, 26, RULE_objectPath);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(194);
                objectType();
                setState(195);
                match(COLON);
                setState(196);
                firstPathComponent();
                setState(198);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 16, _ctx)) {
                case 1: {
                    setState(197);
                    objectPathComponent(0);
                }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ObjectTypeContext extends ParserRuleContext {

        public TerminalNode IdentifierWithoutHyphen() {
            return getToken(StixPatternParser.IdentifierWithoutHyphen, 0);
        }

        public TerminalNode IdentifierWithHyphen() {
            return getToken(StixPatternParser.IdentifierWithHyphen, 0);
        }

        public ObjectTypeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_objectType;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterObjectType(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitObjectType(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitObjectType(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public final ObjectTypeContext objectType() throws RecognitionException {
        ObjectTypeContext _localctx = new ObjectTypeContext(_ctx, getState());
        enterRule(_localctx, 28, RULE_objectType);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(200);
                _la = _input.LA(1);
                if (!(_la == IdentifierWithoutHyphen || _la == IdentifierWithHyphen)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) {
                        matchedEOF = true;
                    }
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class FirstPathComponentContext extends ParserRuleContext {

        public TerminalNode IdentifierWithoutHyphen() {
            return getToken(StixPatternParser.IdentifierWithoutHyphen, 0);
        }

        public TerminalNode StringLiteral() {
            return getToken(StixPatternParser.StringLiteral, 0);
        }

        public FirstPathComponentContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_firstPathComponent;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterFirstPathComponent(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitFirstPathComponent(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitFirstPathComponent(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public final FirstPathComponentContext firstPathComponent() throws RecognitionException {
        FirstPathComponentContext _localctx = new FirstPathComponentContext(_ctx, getState());
        enterRule(_localctx, 30, RULE_firstPathComponent);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(202);
                _la = _input.LA(1);
                if (!(_la == StringLiteral || _la == IdentifierWithoutHyphen)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) {
                        matchedEOF = true;
                    }
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ObjectPathComponentContext extends ParserRuleContext {

        public ObjectPathComponentContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_objectPathComponent;
        }

        public ObjectPathComponentContext() {
        }

        public void copyFrom(ObjectPathComponentContext ctx) {
            super.copyFrom(ctx);
        }
    }

    public static class IndexPathStepContext extends ObjectPathComponentContext {

        public TerminalNode LBRACK() {
            return getToken(StixPatternParser.LBRACK, 0);
        }

        public TerminalNode RBRACK() {
            return getToken(StixPatternParser.RBRACK, 0);
        }

        public TerminalNode IntPosLiteral() {
            return getToken(StixPatternParser.IntPosLiteral, 0);
        }

        public TerminalNode IntNegLiteral() {
            return getToken(StixPatternParser.IntNegLiteral, 0);
        }

        public TerminalNode ASTERISK() {
            return getToken(StixPatternParser.ASTERISK, 0);
        }

        public IndexPathStepContext(ObjectPathComponentContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterIndexPathStep(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitIndexPathStep(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitIndexPathStep(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public static class PathStepContext extends ObjectPathComponentContext {

        public List<ObjectPathComponentContext> objectPathComponent() {
            return getRuleContexts(ObjectPathComponentContext.class);
        }

        public ObjectPathComponentContext objectPathComponent(int i) {
            return getRuleContext(ObjectPathComponentContext.class, i);
        }

        public PathStepContext(ObjectPathComponentContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterPathStep(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitPathStep(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitPathStep(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public static class KeyPathStepContext extends ObjectPathComponentContext {

        public TerminalNode DOT() {
            return getToken(StixPatternParser.DOT, 0);
        }

        public TerminalNode IdentifierWithoutHyphen() {
            return getToken(StixPatternParser.IdentifierWithoutHyphen, 0);
        }

        public TerminalNode StringLiteral() {
            return getToken(StixPatternParser.StringLiteral, 0);
        }

        public KeyPathStepContext(ObjectPathComponentContext ctx) {
            copyFrom(ctx);
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterKeyPathStep(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitKeyPathStep(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitKeyPathStep(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public final ObjectPathComponentContext objectPathComponent() throws RecognitionException {
        return objectPathComponent(0);
    }

    private ObjectPathComponentContext objectPathComponent(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = _ctx;
        int _parentState = getState();
        ObjectPathComponentContext _localctx = new ObjectPathComponentContext(_ctx, _parentState);
        ObjectPathComponentContext _prevctx = _localctx;
        int _startState = 32;
        enterRecursionRule(_localctx, 32, RULE_objectPathComponent, _p);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(210);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                case DOT: {
                    _localctx = new KeyPathStepContext(_localctx);
                    _ctx = _localctx;
                    _prevctx = _localctx;

                    setState(205);
                    match(DOT);
                    setState(206);
                    _la = _input.LA(1);
                    if (!(_la == StringLiteral || _la == IdentifierWithoutHyphen)) {
                        _errHandler.recoverInline(this);
                    } else {
                        if (_input.LA(1) == Token.EOF) {
                            matchedEOF = true;
                        }
                        _errHandler.reportMatch(this);
                        consume();
                    }
                }
                    break;
                case LBRACK: {
                    _localctx = new IndexPathStepContext(_localctx);
                    _ctx = _localctx;
                    _prevctx = _localctx;
                    setState(207);
                    match(LBRACK);
                    setState(208);
                    _la = _input.LA(1);
                    if (!((((_la) & ~0x3f) == 0 && ((1L << _la)
                            & ((1L << IntNegLiteral) | (1L << IntPosLiteral) | (1L << ASTERISK))) != 0))) {
                        _errHandler.recoverInline(this);
                    } else {
                        if (_input.LA(1) == Token.EOF) {
                            matchedEOF = true;
                        }
                        _errHandler.reportMatch(this);
                        consume();
                    }
                    setState(209);
                    match(RBRACK);
                }
                    break;
                default:
                    throw new NoViableAltException(this);
                }
                _ctx.stop = _input.LT(-1);
                setState(216);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 18, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) {
                            triggerExitRuleEvent();
                        }
                        _prevctx = _localctx;
                        {
                            {
                                _localctx = new PathStepContext(
                                        new ObjectPathComponentContext(_parentctx, _parentState));
                                pushNewRecursionContext(_localctx, _startState, RULE_objectPathComponent);
                                setState(212);
                                if (!(precpred(_ctx, 3))) {
                                    throw new FailedPredicateException(this, "precpred(_ctx, 3)");
                                }
                                setState(213);
                                objectPathComponent(4);
                            }
                        }
                    }
                    setState(218);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 18, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            unrollRecursionContexts(_parentctx);
        }
        return _localctx;
    }

    public static class SetLiteralContext extends ParserRuleContext {

        public TerminalNode LPAREN() {
            return getToken(StixPatternParser.LPAREN, 0);
        }

        public TerminalNode RPAREN() {
            return getToken(StixPatternParser.RPAREN, 0);
        }

        public List<PrimitiveLiteralContext> primitiveLiteral() {
            return getRuleContexts(PrimitiveLiteralContext.class);
        }

        public PrimitiveLiteralContext primitiveLiteral(int i) {
            return getRuleContext(PrimitiveLiteralContext.class, i);
        }

        public List<TerminalNode> COMMA() {
            return getTokens(StixPatternParser.COMMA);
        }

        public TerminalNode COMMA(int i) {
            return getToken(StixPatternParser.COMMA, i);
        }

        public SetLiteralContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_setLiteral;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterSetLiteral(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitSetLiteral(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitSetLiteral(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public final SetLiteralContext setLiteral() throws RecognitionException {
        SetLiteralContext _localctx = new SetLiteralContext(_ctx, getState());
        enterRule(_localctx, 34, RULE_setLiteral);
        int _la;
        try {
            setState(232);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 20, _ctx)) {
            case 1:
                enterOuterAlt(_localctx, 1); {
                setState(219);
                match(LPAREN);
                setState(220);
                match(RPAREN);
            }
                break;
            case 2:
                enterOuterAlt(_localctx, 2); {
                setState(221);
                match(LPAREN);
                setState(222);
                primitiveLiteral();
                setState(227);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == COMMA) {
                    {
                        {
                            setState(223);
                            match(COMMA);
                            setState(224);
                            primitiveLiteral();
                        }
                    }
                    setState(229);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(230);
                match(RPAREN);
            }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class PrimitiveLiteralContext extends ParserRuleContext {

        public OrderableLiteralContext orderableLiteral() {
            return getRuleContext(OrderableLiteralContext.class, 0);
        }

        public TerminalNode BoolLiteral() {
            return getToken(StixPatternParser.BoolLiteral, 0);
        }

        public PrimitiveLiteralContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_primitiveLiteral;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterPrimitiveLiteral(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitPrimitiveLiteral(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitPrimitiveLiteral(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public final PrimitiveLiteralContext primitiveLiteral() throws RecognitionException {
        PrimitiveLiteralContext _localctx = new PrimitiveLiteralContext(_ctx, getState());
        enterRule(_localctx, 36, RULE_primitiveLiteral);
        try {
            setState(236);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
            case IntNegLiteral:
            case IntPosLiteral:
            case FloatNegLiteral:
            case FloatPosLiteral:
            case HexLiteral:
            case BinaryLiteral:
            case StringLiteral:
            case TimestampLiteral:
                enterOuterAlt(_localctx, 1); {
                setState(234);
                orderableLiteral();
            }
                break;
            case BoolLiteral:
                enterOuterAlt(_localctx, 2); {
                setState(235);
                match(BoolLiteral);
            }
                break;
            default:
                throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class OrderableLiteralContext extends ParserRuleContext {

        public TerminalNode IntPosLiteral() {
            return getToken(StixPatternParser.IntPosLiteral, 0);
        }

        public TerminalNode IntNegLiteral() {
            return getToken(StixPatternParser.IntNegLiteral, 0);
        }

        public TerminalNode FloatPosLiteral() {
            return getToken(StixPatternParser.FloatPosLiteral, 0);
        }

        public TerminalNode FloatNegLiteral() {
            return getToken(StixPatternParser.FloatNegLiteral, 0);
        }

        public StringLiteralContext stringLiteral() {
            return getRuleContext(StringLiteralContext.class, 0);
        }

        public TerminalNode BinaryLiteral() {
            return getToken(StixPatternParser.BinaryLiteral, 0);
        }

        public TerminalNode HexLiteral() {
            return getToken(StixPatternParser.HexLiteral, 0);
        }

        public TerminalNode TimestampLiteral() {
            return getToken(StixPatternParser.TimestampLiteral, 0);
        }

        public OrderableLiteralContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_orderableLiteral;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).enterOrderableLiteral(this);
            }
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StixPatternListener) {
                ((StixPatternListener) listener).exitOrderableLiteral(this);
            }
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StixPatternVisitor) {
                return ((StixPatternVisitor<? extends T>) visitor).visitOrderableLiteral(this);
            } else {
                return visitor.visitChildren(this);
            }
        }
    }

    public final OrderableLiteralContext orderableLiteral() throws RecognitionException {
        OrderableLiteralContext _localctx = new OrderableLiteralContext(_ctx, getState());
        enterRule(_localctx, 38, RULE_orderableLiteral);
        try {
            setState(246);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
            case IntPosLiteral:
                enterOuterAlt(_localctx, 1); {
                setState(238);
                match(IntPosLiteral);
            }
                break;
            case IntNegLiteral:
                enterOuterAlt(_localctx, 2); {
                setState(239);
                match(IntNegLiteral);
            }
                break;
            case FloatPosLiteral:
                enterOuterAlt(_localctx, 3); {
                setState(240);
                match(FloatPosLiteral);
            }
                break;
            case FloatNegLiteral:
                enterOuterAlt(_localctx, 4); {
                setState(241);
                match(FloatNegLiteral);
            }
                break;
            case StringLiteral:
                enterOuterAlt(_localctx, 5); {
                setState(242);
                stringLiteral();
            }
                break;
            case BinaryLiteral:
                enterOuterAlt(_localctx, 6); {
                setState(243);
                match(BinaryLiteral);
            }
                break;
            case HexLiteral:
                enterOuterAlt(_localctx, 7); {
                setState(244);
                match(HexLiteral);
            }
                break;
            case TimestampLiteral:
                enterOuterAlt(_localctx, 8); {
                setState(245);
                match(TimestampLiteral);
            }
                break;
            default:
                throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
        switch (ruleIndex) {
        case 1:
            return observationExpressions_sempred((ObservationExpressionsContext) _localctx, predIndex);
        case 2:
            return observationExpressionOr_sempred((ObservationExpressionOrContext) _localctx, predIndex);
        case 3:
            return observationExpressionAnd_sempred((ObservationExpressionAndContext) _localctx, predIndex);
        case 4:
            return observationExpression_sempred((ObservationExpressionContext) _localctx, predIndex);
        case 5:
            return comparisonExpression_sempred((ComparisonExpressionContext) _localctx, predIndex);
        case 6:
            return comparisonExpressionAnd_sempred((ComparisonExpressionAndContext) _localctx, predIndex);
        case 16:
            return objectPathComponent_sempred((ObjectPathComponentContext) _localctx, predIndex);
        }
        return true;
    }

    private boolean observationExpressions_sempred(ObservationExpressionsContext _localctx, int predIndex) {
        switch (predIndex) {
        case 0:
            return precpred(_ctx, 2);
        }
        return true;
    }

    private boolean observationExpressionOr_sempred(ObservationExpressionOrContext _localctx, int predIndex) {
        switch (predIndex) {
        case 1:
            return precpred(_ctx, 2);
        }
        return true;
    }

    private boolean observationExpressionAnd_sempred(ObservationExpressionAndContext _localctx, int predIndex) {
        switch (predIndex) {
        case 2:
            return precpred(_ctx, 2);
        }
        return true;
    }

    private boolean observationExpression_sempred(ObservationExpressionContext _localctx, int predIndex) {
        switch (predIndex) {
        case 3:
            return precpred(_ctx, 3);
        case 4:
            return precpred(_ctx, 2);
        case 5:
            return precpred(_ctx, 1);
        }
        return true;
    }

    private boolean comparisonExpression_sempred(ComparisonExpressionContext _localctx, int predIndex) {
        switch (predIndex) {
        case 6:
            return precpred(_ctx, 2);
        }
        return true;
    }

    private boolean comparisonExpressionAnd_sempred(ComparisonExpressionAndContext _localctx, int predIndex) {
        switch (predIndex) {
        case 7:
            return precpred(_ctx, 2);
        }
        return true;
    }

    private boolean objectPathComponent_sempred(ObjectPathComponentContext _localctx, int predIndex) {
        switch (predIndex) {
        case 8:
            return precpred(_ctx, 3);
        }
        return true;
    }

    public static final String _serializedATN = "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\38\u00fb\4\2\t\2\4"
            + "\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"
            + "\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"
            + "\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3"
            + "\64\n\3\f\3\16\3\67\13\3\3\4\3\4\3\4\3\4\3\4\3\4\7\4?\n\4\f\4\16\4B\13"
            + "\4\3\5\3\5\3\5\3\5\3\5\3\5\7\5J\n\5\f\5\16\5M\13\5\3\6\3\6\3\6\3\6\3\6"
            + "\3\6\3\6\3\6\3\6\5\6X\n\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6`\n\6\f\6\16\6c\13"
            + "\6\3\7\3\7\3\7\3\7\3\7\3\7\7\7k\n\7\f\7\16\7n\13\7\3\b\3\b\3\b\3\b\3\b"
            + "\3\b\7\bv\n\b\f\b\16\by\13\b\3\t\3\t\5\t}\n\t\3\t\3\t\3\t\3\t\3\t\5\t"
            + "\u0084\n\t\3\t\3\t\3\t\3\t\3\t\5\t\u008b\n\t\3\t\3\t\3\t\3\t\3\t\5\t\u0092"
            + "\n\t\3\t\3\t\3\t\3\t\3\t\5\t\u0099\n\t\3\t\3\t\3\t\3\t\3\t\5\t\u00a0\n"
            + "\t\3\t\3\t\3\t\3\t\3\t\5\t\u00a7\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"
            + "\t\5\t\u00b2\n\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r"
            + "\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\5\17\u00c9\n\17\3\20\3\20\3\21"
            + "\3\21\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00d5\n\22\3\22\3\22\7\22\u00d9"
            + "\n\22\f\22\16\22\u00dc\13\22\3\23\3\23\3\23\3\23\3\23\3\23\7\23\u00e4"
            + "\n\23\f\23\16\23\u00e7\13\23\3\23\3\23\5\23\u00eb\n\23\3\24\3\24\5\24"
            + "\u00ef\n\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u00f9\n\25\3"
            + "\25\2\t\4\6\b\n\f\16\"\26\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&"
            + "(\2\b\3\2!\"\3\2#&\4\2\4\4\6\6\3\2\37 \4\2\t\t\37\37\4\2\3\4\64\64\2\u010b"
            + "\2*\3\2\2\2\4-\3\2\2\2\68\3\2\2\2\bC\3\2\2\2\nW\3\2\2\2\fd\3\2\2\2\16"
            + "o\3\2\2\2\20\u00b1\3\2\2\2\22\u00b3\3\2\2\2\24\u00b5\3\2\2\2\26\u00b7"
            + "\3\2\2\2\30\u00bc\3\2\2\2\32\u00c0\3\2\2\2\34\u00c4\3\2\2\2\36\u00ca\3"
            + "\2\2\2 \u00cc\3\2\2\2\"\u00d4\3\2\2\2$\u00ea\3\2\2\2&\u00ee\3\2\2\2(\u00f8"
            + "\3\2\2\2*+\5\4\3\2+,\7\2\2\3,\3\3\2\2\2-.\b\3\1\2./\5\6\4\2/\65\3\2\2"
            + "\2\60\61\f\4\2\2\61\62\7\17\2\2\62\64\5\4\3\5\63\60\3\2\2\2\64\67\3\2"
            + "\2\2\65\63\3\2\2\2\65\66\3\2\2\2\66\5\3\2\2\2\67\65\3\2\2\289\b\4\1\2"
            + "9:\5\b\5\2:@\3\2\2\2;<\f\4\2\2<=\7\r\2\2=?\5\6\4\5>;\3\2\2\2?B\3\2\2\2"
            + "@>\3\2\2\2@A\3\2\2\2A\7\3\2\2\2B@\3\2\2\2CD\b\5\1\2DE\5\n\6\2EK\3\2\2"
            + "\2FG\f\4\2\2GH\7\f\2\2HJ\5\b\5\5IF\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2"
            + "\2L\t\3\2\2\2MK\3\2\2\2NO\b\6\1\2OP\7.\2\2PQ\5\f\7\2QR\7-\2\2RX\3\2\2"
            + "\2ST\7,\2\2TU\5\4\3\2UV\7+\2\2VX\3\2\2\2WN\3\2\2\2WS\3\2\2\2Xa\3\2\2\2"
            + "YZ\f\5\2\2Z`\5\26\f\2[\\\f\4\2\2\\`\5\30\r\2]^\f\3\2\2^`\5\32\16\2_Y\3"
            + "\2\2\2_[\3\2\2\2_]\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2b\13\3\2\2\2c"
            + "a\3\2\2\2de\b\7\1\2ef\5\16\b\2fl\3\2\2\2gh\f\4\2\2hi\7\r\2\2ik\5\f\7\5"
            + "jg\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2m\r\3\2\2\2nl\3\2\2\2op\b\b\1"
            + "\2pq\5\20\t\2qw\3\2\2\2rs\f\4\2\2st\7\f\2\2tv\5\16\b\5ur\3\2\2\2vy\3\2"
            + "\2\2wu\3\2\2\2wx\3\2\2\2x\17\3\2\2\2yw\3\2\2\2z|\5\34\17\2{}\7\16\2\2"
            + "|{\3\2\2\2|}\3\2\2\2}~\3\2\2\2~\177\t\2\2\2\177\u0080\5&\24\2\u0080\u00b2"
            + "\3\2\2\2\u0081\u0083\5\34\17\2\u0082\u0084\7\16\2\2\u0083\u0082\3\2\2"
            + "\2\u0083\u0084\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\t\3\2\2\u0086\u0087"
            + "\5(\25\2\u0087\u00b2\3\2\2\2\u0088\u008a\5\34\17\2\u0089\u008b\7\16\2"
            + "\2\u008a\u0089\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d"
            + "\7\26\2\2\u008d\u008e\5$\23\2\u008e\u00b2\3\2\2\2\u008f\u0091\5\34\17"
            + "\2\u0090\u0092\7\16\2\2\u0091\u0090\3\2\2\2\u0091\u0092\3\2\2\2\u0092"
            + "\u0093\3\2\2\2\u0093\u0094\7\20\2\2\u0094\u0095\5\24\13\2\u0095\u00b2"
            + "\3\2\2\2\u0096\u0098\5\34\17\2\u0097\u0099\7\16\2\2\u0098\u0097\3\2\2"
            + "\2\u0098\u0099\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\7\21\2\2\u009b"
            + "\u009c\5\24\13\2\u009c\u00b2\3\2\2\2\u009d\u009f\5\34\17\2\u009e\u00a0"
            + "\7\16\2\2\u009f\u009e\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\3\2\2\2"
            + "\u00a1\u00a2\7\23\2\2\u00a2\u00a3\5\24\13\2\u00a3\u00b2\3\2\2\2\u00a4"
            + "\u00a6\5\34\17\2\u00a5\u00a7\7\16\2\2\u00a6\u00a5\3\2\2\2\u00a6\u00a7"
            + "\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\7\22\2\2\u00a9\u00aa\5\24\13"
            + "\2\u00aa\u00b2\3\2\2\2\u00ab\u00ac\7,\2\2\u00ac\u00ad\5\f\7\2\u00ad\u00ae"
            + "\7+\2\2\u00ae\u00b2\3\2\2\2\u00af\u00b0\7\24\2\2\u00b0\u00b2\5\34\17\2"
            + "\u00b1z\3\2\2\2\u00b1\u0081\3\2\2\2\u00b1\u0088\3\2\2\2\u00b1\u008f\3"
            + "\2\2\2\u00b1\u0096\3\2\2\2\u00b1\u009d\3\2\2\2\u00b1\u00a4\3\2\2\2\u00b1"
            + "\u00ab\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\21\3\2\2\2\u00b3\u00b4\t\3\2"
            + "\2\u00b4\23\3\2\2\2\u00b5\u00b6\7\t\2\2\u00b6\25\3\2\2\2\u00b7\u00b8\7"
            + "\27\2\2\u00b8\u00b9\7\13\2\2\u00b9\u00ba\7\30\2\2\u00ba\u00bb\7\13\2\2"
            + "\u00bb\27\3\2\2\2\u00bc\u00bd\7\34\2\2\u00bd\u00be\t\4\2\2\u00be\u00bf"
            + "\7\31\2\2\u00bf\31\3\2\2\2\u00c0\u00c1\7\35\2\2\u00c1\u00c2\7\4\2\2\u00c2"
            + "\u00c3\7\36\2\2\u00c3\33\3\2\2\2\u00c4\u00c5\5\36\20\2\u00c5\u00c6\7("
            + "\2\2\u00c6\u00c8\5 \21\2\u00c7\u00c9\5\"\22\2\u00c8\u00c7\3\2\2\2\u00c8"
            + "\u00c9\3\2\2\2\u00c9\35\3\2\2\2\u00ca\u00cb\t\5\2\2\u00cb\37\3\2\2\2\u00cc"
            + "\u00cd\t\6\2\2\u00cd!\3\2\2\2\u00ce\u00cf\b\22\1\2\u00cf\u00d0\7)\2\2"
            + "\u00d0\u00d5\t\6\2\2\u00d1\u00d2\7.\2\2\u00d2\u00d3\t\7\2\2\u00d3\u00d5"
            + "\7-\2\2\u00d4\u00ce\3\2\2\2\u00d4\u00d1\3\2\2\2\u00d5\u00da\3\2\2\2\u00d6"
            + "\u00d7\f\5\2\2\u00d7\u00d9\5\"\22\6\u00d8\u00d6\3\2\2\2\u00d9\u00dc\3"
            + "\2\2\2\u00da\u00d8\3\2\2\2\u00da\u00db\3\2\2\2\u00db#\3\2\2\2\u00dc\u00da"
            + "\3\2\2\2\u00dd\u00de\7,\2\2\u00de\u00eb\7+\2\2\u00df\u00e0\7,\2\2\u00e0"
            + "\u00e5\5&\24\2\u00e1\u00e2\7*\2\2\u00e2\u00e4\5&\24\2\u00e3\u00e1\3\2"
            + "\2\2\u00e4\u00e7\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6"
            + "\u00e8\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e8\u00e9\7+\2\2\u00e9\u00eb\3\2"
            + "\2\2\u00ea\u00dd\3\2\2\2\u00ea\u00df\3\2\2\2\u00eb%\3\2\2\2\u00ec\u00ef"
            + "\5(\25\2\u00ed\u00ef\7\n\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ed\3\2\2\2\u00ef"
            + "\'\3\2\2\2\u00f0\u00f9\7\4\2\2\u00f1\u00f9\7\3\2\2\u00f2\u00f9\7\6\2\2"
            + "\u00f3\u00f9\7\5\2\2\u00f4\u00f9\5\24\13\2\u00f5\u00f9\7\b\2\2\u00f6\u00f9"
            + "\7\7\2\2\u00f7\u00f9\7\13\2\2\u00f8\u00f0\3\2\2\2\u00f8\u00f1\3\2\2\2"
            + "\u00f8\u00f2\3\2\2\2\u00f8\u00f3\3\2\2\2\u00f8\u00f4\3\2\2\2\u00f8\u00f5"
            + "\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f7\3\2\2\2\u00f9)\3\2\2\2\31\65"
            + "@KW_alw|\u0083\u008a\u0091\u0098\u009f\u00a6\u00b1\u00c8\u00d4\u00da\u00e5" + "\u00ea\u00ee\u00f8";
    public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}
